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
jal _suma_s0
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
move $fp, $sp 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
sw $s0 , -12($sp)
move $s0, $a0
sw $s1 , -16($sp)
move $s1, $a1
sub $sp, $sp, 20
lw $t0, _x
lw $t1, _y
add $t2, $t0, $t1
sw $t2, _z
L4: 
move $v0, null
L5: 

_fin_suma_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
li $v0, 10
syscall
