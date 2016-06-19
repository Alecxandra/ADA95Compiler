.data
_i:	.word	0
_x1:	.word	0
_msg0:	.asciiz	"El resultado es: "

.text
.globl main

main: 

_principal_s0: 
li$t0, 20
sw $t0, _x1
L0: 
li $t0,5
lw $t1, _x1
mul $t2, $t0, $t1
li $t0, 10
add $t1, $t2, $t0
move $a0, $t1
li $t0,6
lw $t1, _x1
div $t2, $t0, $t1
li $t0, 9
add $t1, $t2, $t0
move $a1, $t1
sw null, _i
L1: 
li $v0, 4
la $a0, _msg0
syscall
L2: 
li $v0, 1
lw $a0, _i
syscall
L3: 
_suma_s0: 
lw $t0, _x
lw $t1, _y
add $t2, $t0, $t1
sw $t2, _z
L4: 
move $v0, null
L5: 
li $v0, 10
syscall
