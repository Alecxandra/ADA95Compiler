.data
_i:	.word	0
_msg0:	.asciiz	"El resultado es: "

.text
.globl main

main: 

_principal_s0: 
li $t0, 5
move $a0, $t0
li $t1, 6
move $a1, $t1
sw null, _i
L0: 
li $v0, 4
la $a0, _msg0
syscall
L1: 
li $v0, 1
lw $a0, _i
syscall
L2: 
_suma_s0: 
lw $t2, _x
lw $t3, _y
add $t4, $t2, $t3
sw $t4, _z
L3: 
move $v0, null
L4: 
li $v0, 10
syscall
