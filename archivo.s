.data
_i:	.word	0
_x:	.word	0

.text
.globl main

main: 

_principal_s0: 
li $t0, 0
sw $t0, _i
L0: 
li $t0, 1
sw $t0, _i
L1: 
lw $t0, _i
li $t1,5
beq $t0, $t1, L2
b L5
L2: 
li $v0, 5
syscall
sw $v0, _x
L4: 
L3: 
lw $t0, _i
li $t1, 1
add $t2, $t0, $t1
sw $t2,_i
b L1
L5: 
li $v0, 10
syscall
