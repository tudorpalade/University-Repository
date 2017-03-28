.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern printf: proc
extern fprintf: proc
extern fscanf: proc
extern fopen: proc
extern fclose: proc
extern exit: proc
extern scanf:proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
mode_read DB "r" ,0
mode_read_out DB "w",0
file_name DB "fisierIn1.txt" ,0
file_name_out DB "fisierOut1.txt",0
file_name_dictionar1 DB "fisierDictionar1.txt",0
buffer DB 20 dup(0)
buf db 20 dup(0)
format DB "%s",0
formatPentruDictionar db "%s ",0
formatOUT DB "%c%c ",0
formatOUTdictionar DB "%c%c ",10,13,0
ultimulASCII DB 122
numarCuvinte DB 0
iterator db 0
lungimeBuffer1 db 0
lungimeBuffer2 db 0
nr_cuvinte dw 0
rezultat dw 0
hotarare db 0
format_hotarare db "%d" ,0
file_name_dictionar2 DB "fisierDictionar2.txt",0
file_name_fisier_sursa DB "fisierSursa2.txt",0
file_name_fisier_out DB "fisierOut2.txt",0
buffer2 DB 20 dup(0)
formatOUT2 DB "%s ",0
auxiliar DD 2 dup(0)

structura struct 
	cuvant db 20 dup(0)
	cod db 2 dup(0)
structura ends

dictionar structura 200 dup(<>)	
.code
start:
existentaCuvant MACRO nr_cuvinte,buf
LOCAL final,inceput,aici2,FORlungime,FORlungime2,lungime,au_fost_egale,nu_au_fost_egale,aici
	cmp nr_cuvinte,0    
	je final            
	mov al , 1
	mov iterator,al
	lea ESI,dictionar
	inceput:
		mov bl , iterator    
		cmp bl , nr_cuvinte  
		ja final             
		lea ecx,buf        
		
		mov al,0
		mov lungimeBuffer1,al
		FORlungime:
			mov AL,byte ptr[ECX]
			inc lungimeBuffer1
			inc ECX
			cmp AL,0
			jne FORlungime  
		dec lungimeBuffer1	
		
		mov ECX,ESI
		mov al,0
		mov lungimeBuffer2,al
		FORlungime2:
			mov AL,byte ptr[ECX]
			inc lungimeBuffer2
			inc ECX
			cmp AL,0
			jne FORlungime2  
		dec lungimeBuffer2	
		mov ECX,0
		mov CL,lungimeBuffer1
		mov CH,lungimeBuffer2
		cmp CL,CH
		jne aici
		mov ecx,0
		mov cl,lungimeBuffer1
		lea EDI, buffer
		push ESI
		repe cmpsb
		pop ESI
		je au_fost_egale
		aici:
			add esi,22
			inc iterator
	jmp inceput
	au_fost_egale:
	mov eax,1
	jmp final
	nu_au_fost_egale:
	mov eax,0
	jmp final
	final:
ENDM

tiparesteInOut MACRO buf2
LOCAL gasit_potrivire, inceput , FORlungime,FORlungime2,aici
	lea EBX,dictionar
	inceput:
		push ESI
		push EDI
		lea ECX, buf2
		
		mov al,0
		mov lungimeBuffer1,al
		FORlungime:
			mov AL,byte ptr[ECX]
			inc lungimeBuffer1
			inc ECX
			cmp AL,0
			jne FORlungime  ;in momentul asta , in lungimeBuffer, avem lungimea lui buf
		dec lungimeBuffer1	
		
		mov ECX,EBX
		mov al,0
		mov lungimeBuffer2,al
		FORlungime2:
			mov AL,byte ptr[ECX]
			inc lungimeBuffer2
			inc ECX
			cmp AL,0
			jne FORlungime2  
		dec lungimeBuffer2	
		
		mov CL,lungimeBuffer1
		mov CH,lungimeBuffer2
		cmp CL,CH
		pop EDI
		pop ESI
		jne aici
		push ESI
		push EDI
		mov ecx,0
		mov cl,lungimeBuffer1
		lea EDX, buffer
		mov ESI,EBX
		mov EDI,EDX
		repe cmpsb
		pop EDI
		pop ESI
		je gasit_potrivire
		aici:
			add EBX,22
	jmp inceput
	gasit_potrivire:
		mov AL, byte ptr[EBX+20]
		xor ecx,ecx
		mov cl,al
		push ecx
		mov AL, byte ptr[EBX+21]
		xor ecx,ecx
		mov cl,aL
		push ecx
		push offset formatOUT
		push EDI
		call fprintf
		add ESP,16
ENDM
	
	push offset hotarare                  ;***************************************************************************
	push offset format_hotarare           ;****
	call scanf                            ;****
	add ESP,8                             ;**** PARTEA CARE DECIDE CARE ALGORITM SE VA EXECUTA: COMPRIMARE(0)/DECOMPRIMARE(1)
	mov al,hotarare                       ;****
	cmp al, 1                             ;****
	je decomprimare                         ;****************************************************************************

;************************************************                   PARTEA DE COMPRIMARE                   ************************************************************
	
	push offset mode_read                 ;****************************************
 	push offset file_name                 ;****DESCHIDEM FISIERUL SURSA, DIN CARE CITIM TEXTUL CARE DORIM SA FIE COMPRIMAT
	call fopen                            ;****************************************
	add ESP,8                             ;****GOLIM STIVA DE PARAMETRII CARE AU FOST PUSI PENTRU CITIRE
	mov ESI,EAX                           ;****PASTRAM INDEXUL FISIERULUI IN REGISTRUL ESI
	mov EBX,0                             ;****INITIALIZARE
	mov EDX,0                             ;****INITIALIZARE
	lea EDI, dictionar                    ;****PASTRAM INDEXUL DICTIONARULUI IN REGISTRUL EDI
		mov DL , 97                       ;****CONTOR PENTRU PRIMUL CARACTER ASCII DIN CODIFICARE
		for1:                             ;****
			mov BL, 97                    ;****CONTOR PENTRU AL DOILEA CARACTER ASCII DIN CODIFICARE
			for2:                         ;****
					push EDX              ;****PUNEM PE STIVA VALOAREA DIN EDX, PENTRU A NU SE PIERDE DUPA APELUL DE MAI JOS
					push offset buffer    ;****************************************
					push offset format    ;****CITIM DIN FISIER CUVANT CU CUVANT SI PASTRAM IN BUFFER
					push ESI              ;****
					call fscanf           ;****************************************
					add esp , 12          ;****GOLIM STIVA DE PARAMETRII CARE AU FOST PUSI PENTRU CITIRE
					pop EDX               ;****RETRAGEM VALOAREA DE PE STIVA IN EDX( CEA CARE A FOST PUSA PENTRU A NU SE PIERDE)
					test eax,eax          ;****VERIFICAM SFARSITUL FISIERULUI
					js inchidere_fisier2  ;****DACA E SFARSIT DE FISIER, IL INCHIDEM
					mov eax,0             ;****INITIALIZAM REGISTRUL EAX CU 0, IANINTE DE APELUL DE MACROU 
					push EBX              ;****************************************
					push ESI              ;****PUNEM PE STIVA VALORILE REGISTRILOR PENTRU CA ACESTIA VOR FI SCHIMBATI IN MACROU
					push EDI              ;****************************************
					existentaCuvant numarCuvinte,buffer
					pop EDI               ;**************************************** 
					pop ESI               ;****RETRAGEM VALORILE DE PE STIVA SI LE PUNEM INAPOI IN REGISTRII
					pop EBX               ;****************************************
					cmp eax,1             ;****ACEASTA COMPARATIE NE DETERMINA DACA CUVANTUL DIN FISIER EXISTA DEJA IN DICTIONAR
					je cuvantulAreDuplicat;****DACA EXISTA SARIM LA ETICHETA 
										  ;****DACA NU EXISTA :
						lea ECX,buffer    			 ;****PASTRAM IN ECX CUVANTUL 
						mov EBP,0         			 ;****INCREMENTAM EBP CU 0 (EBP REPREZINTA INDEXUL DIN CUVANT)
						forCuvant:        			 ;****************************************
							mov AL , byte ptr [ECX]  ;****
							mov [EDI+EBP] , AL       ;****
							inc EBP                  ;****CITIM LITERA CU LITERA DIN CUVANT PENTRU A-L INTRODUCE IN DICTIONAR 
							inc ECX                  ;****
							cmp AL , 0               ;****
							jne forCuvant            ;****************************************
						mov AH,DL                    ;****IN AH PASTRAM VALOAREA PRIMEI LITERE DIN CODUL ASCII ATRIBUIT CUVANTULUI 
						mov AL,BL                    ;****IN AL PASTRAM VALOAREA CELEI DE A DOUA LITERE DIN CODUL ASCII ATRIBUIT CUVANTULUI 
						mov [EDI+20] ,AX             ;****INTRODUCEM IN DICTIONAR CODUL ATRIBUIT PENTRU CUVANT ( DEFINITIVAM RELATIA CUVANT - COD )
						add EDI,22                   ;****TRECEM LA URMATOAREA POZITIE DIN DICTIONAR ( POZITIE CARE VA FI COMPLETATA ULTERIOR )
					inc numarCuvinte	             ;****INCREMENTAM NUMARUL DE CUVINTE ACTUAL ( IN ACEASTA VARIABILA LA SFARSIT VOM AVEA TOTATALITATEA CUVINTELOR DIN DICTIONAR )
				inc BL                               ;****INCREMENTAM CONTORUL PENTRU A DOUA POZTIE DIN CODIFICARE
				cmp BL,ultimulASCII                  ;****COMPARAM A DOUA LITERA DIN CODIFICARE CU ULTIMA LITERA DIN ALFABET 
				cuvantulAreDuplicat:                 ;****
				jbe for2                             ;****DACA INCA NU S-A AJUNS LA ULTIMA LITERA DIN ALFABET , CONTINUAM
			mov BL , 97                              ;****DACA S-A AJUNS BL = Z , ATUNCI IL FACEM PE BL DIN NOU a
			inc DL                                   ;****INCREMENTAM CONTORUL OENTRU PRIMA POZITIE DIN CODIFICARE , DECI URMATORUL COD VA FI : ba
			cmp DL,ultimulASCII                      ;****DACA INCA NU S-A AJUNS LA ULTIMA LITERA DIN ALFABET, CONTINUAM (PENTRU PRIMA POZITIE ( AR INSEMNA SA AVEM 256 CUVINTE IN DICTIONAR))
			jbe for1                                 ;****
		 
	inchidere_fisier2:                               ;**************************** 
		push ESI                                     ;****INCHIDEM FISIERUL SURSA SI GOLIM STIVA
		call fclose                                  ;****
		add ESP,4                                    ;**************************** 
                                                     ;****CODIFICAREA PROPRIU-ZISA A TEXTULUI
	push offset mode_read_out                        ;****************************
	push offset file_name_out                        ;****
	call fopen                                       ;****DESCHIDEREA FISIERULUI IN CARE VA FI CODIFICAREA
	add ESP,8                                        ;****
	mov EDI,EAX                                      ;****************************
	
	push offset mode_read                            ;****************************
	push offset file_name                            ;****
	call fopen                                       ;****REDESCHIDEM FISIERUL SURSA, PENTRU A PUTEA SA O LUAM DE LA INCEPUT
	add ESP,8                                        ;****
	mov ESI,EAX                                      ;****************************
	
	repetare:
		push offset buffer                           ;****************************
		push offset format                           ;****
		push ESI                                     ;****CITIM DIN FISIERUL SURSA PANA LA SFARSITUL LUI
		call fscanf                                  ;****
		add esp , 12                                 ;****
		test eax,eax                                 ;****************************
		js terminare_program                         ;****SE TERMINA PROGRAMUL (DACA E FINAL DE FISIER SURSA)
		tiparesteInOut buffer                        ;****TIPARIM IN FISIERUL OUT
	jmp repetare                                     ;****
	
	terminare_program:                               ;****************************
	push EDI                                         ;****
	call fclose                                      ;****
	add ESP,4                                        ;****INCHIDEM FISIERUL SURSA SI FISIERUL OUT
	
	push ESI                                         ;****
	call fclose                                      ;****
	add ESP,4                                        ;****************************
	push offset mode_read_out                        ;****************************
	push offset file_name_dictionar1                 ;****
	call fopen                                       ;****DESCHIDEM UN NOU FISIER IN MODE WRITE. IN ACESTA SE TIPARESTE DICTIONARUL
	add ESP,8                                        ;****
	mov ESI,EAX                                      ;****************************
	
	lea EBX,dictionar                                ;****PASTRAM IN EBX ADRESA DE INCEPUT A DICTIONARULUI
	mov ECX,0                                        ;****
	mov CL,1                                         ;****
	completareDictionar:                             ;****REPETAM SCRIEREA IN FISIER PANA CAND AJUNGEM LA ULTIMUL CUVANT DIN DICTIONAR
		cmp CL, numarCuvinte                         ;****
		ja closefisier                               ;****DACA E ULTIMUL CUVANT, SE TERMINA PROGRAMUL
		push ECX                                     ;****PASTRAM VALOAREA DIN ECX PE STIVA, PENTRU CA SE VA MODIFICA LA APLEAREA FUNCTIEI fprintf
		push EBX                                     ;****************************
		push offset formatPentruDictionar            ;****
		push ESI                                     ;****TIPARIM CUVANTUL IN DICTIONAR SI GOLIM STIVA DE PARAMETRII
		call fprintf                                 ;****
		add ESP,12                                   ;****************************
		
		mov AL, byte ptr[EBX+20]                     ;****************************
		xor ecx,ecx                                  ;****
		mov cl,al                                    ;****
		push ecx                                     ;****
		mov AL, byte ptr[EBX+21]                     ;****
		xor ecx,ecx                                  ;****IN FISIERUL DICTIONARULUI II ATRIBUIM CUVANTULUI CODUL
		mov cl,aL                                    ;****
		push ecx                                     ;****
		push offset formatOUTdictionar               ;****
		push ESI                                     ;****
		call fprintf                                 ;**** 
		add ESP,16                                   ;****GOLIM STIVA
		pop ECX                                      ;****RETRAGEM VALOAREA LUI ECX DE PE STIVA
		
		add EBX,22                                   ;****TRECEM LA URMATORUL CUVANT DIN DICTIONAR
		inc CL                                       ;****INCREMENTAM CONTORUL CUVINTELOR DEJA INTRODUSE IN DICTIONAR
	jmp completareDictionar                          ;****
	closefisier:                                     ;****
		push ESI                                         ;************************
		call fclose                                      ;****INCHIDEM FISIERUL DICTIONAR
		add ESP,4                                        ;************************
	jmp FINAL                                        ;****SE TERMINA APLICATIA
;***********************************************************                      PARTEA DE DECOMPRIMARE	         **************************************************************
decomprimare: 
creareDictionar MACRO 
LOCAL forCuvant
		push offset buffer
		push offset format
		push ESI
		call fscanf
		add esp , 12
		test eax,eax  
		js final2
		lea ECX,buffer
		mov EBP,0
		forCuvant:
			mov AL , byte ptr [ECX]
			mov [EDI+EBP] , AL
			inc EBP
			inc ECX
			cmp AL , 0
		jne forCuvant
ENDM

cautareCuvant MACRO
LOCAL inceput, gasit_potrivire
		lea EBX, dictionar
		inceput:
			mov EAX,0
			mov AH, [EBX+21]
			mov AL, [EBX+20]
			push ESI
			push EDI
			mov auxiliar, EAX
			lea ESI, auxiliar
			lea EDI, buffer
			mov ECX, 2
			repe cmpsb
			pop EDI
			pop ESI
			je gasit_potrivire
			add EBX,22
		jmp inceput
		gasit_potrivire:
			push EBX
			push offset formatOUT2
			push EDI
			call fprintf
ENDM

	push offset mode_read
	push offset file_name_dictionar2	
	call fopen
	add ESP,8    
	mov ESI,EAX
	lea EDI, dictionar
	construire_dictionar:
		creareDictionar
		add EDI,20
		creareDictionar
		add EDI,2
	jmp construire_dictionar
	final2:
	push ESI
	call fclose
	add ESP,4   
	
	push offset mode_read
	push offset file_name_fisier_sursa
	call fopen
	add ESP,8
	mov ESI,EAX
	
	push offset mode_read_out
	push offset file_name_fisier_out
	call fopen
	add ESP,8
	mov EDI,EAX
	
	citireFisier:
		push offset buffer
		push offset format
		push ESI
		call fscanf
		add esp , 12
		test eax,eax  
		js inchidere_fisier
	
		cautareCuvant
	
	jmp citireFisier
	
	inchidere_fisier:
		push ESI
		call fclose
		add ESP,4
		push EDI
		call fclose
		add ESP,4
	
	
	FINAL:
	push 0
	call exit
end start