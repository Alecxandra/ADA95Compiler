.data
_i:	.word	0
_x:	.word	0
_msg0:	.asciiz	"hola"
_msg1:	.asciiz	"\n"

.text
.globl main

main: 

_principal_s0: 
li $t0, 1
sw $t0, _i
L0: 
L1: 
li $v0, 4
la $a0, _msg0
syscall
L2: 
li $v0, 4
la $a0, _msg1
syscall
L3: 
lw $t0, _i
li $t1,5
beq $t0, $t1, L4
b L5
L4: 
b 
L5: 
lw $t0, _i
li $t1, 1
add $t2, $t0, $t1
sw $t2, _i
L6: 
b L1
L7: 
li $v0, 10
syscall
