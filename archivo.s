.data
_i:	.word	0
_msg0:	.asciiz	"Hola"
_msg1:	.asciiz	"\n"

.text
.globl main

main: 

_principal_s0: 
li $v0, 4
la $a0, _msg0
syscall
L0: 
li$t0,0
sw $t0, _i
L1: 
L4: 
b L3
L3: 
lw $t0, _i
li $t1,10
bge $t0, $t1, L5
b L9
L5: 
lw $t0, _i
li $t1,15
ble $t0, $t1, L6
b L9
L6: 
li $v0, 1
lw $a0, _i
syscall
L7: 
li $v0, 4
la $a0, _msg1
syscall
L8: 
L9: 
lw $t0, _i
li $s0, 1
add $t1, $t0, $s0
sw $t1, _i
L10: 
lw $t0, _i
li $t1,30
beq $t0, $t1, L11
b L12
L11: 
b L13
L12: 
b L4
L13: 
li $v0, 10
syscall
