.data
_resultado:	.word	0
_x:	.word	0
_y:	.word	0
_msg0:	.asciiz	"Ingrese un numero: "
_msg1:	.asciiz	"el resultado es: "

.text
.globl main

main: 

_principal_s0: 
li $v0, 4
la $a0, _msg0
syscall
L0: 
li $v0, 5
syscall
sw $v0, _x
L1: 
li $v0, 4
la $a0, _msg0
syscall
L2: 
li $v0, 5
syscall
sw $v0, _y
L3: 
lw $t0, _x
move $a0, $t0
lw $t0, _y
move $a1, $t0
jal _resta_s0
sw $v0, _resultado
L4: 
li $v0, 4
la $a0, _msg1
syscall
L5: 
li $v0, 1
lw $a0, _resultado
syscall
L6: 
li $v0, 10
syscall
_resta_s0: 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
move $fp, $sp 
sw $s0 , -12($sp)
move $s0, $a0
sw $s1 , -16($sp)
move $s1, $a1
sub $sp, $sp, 20
move $t0, $s0
move $t1, $s1
sub $t2, $t0, $t1
sw $t2, -20($fp)
L7: 
lw $t0, -20($fp)
move $v0, $t0
b _fin_resta_s0
L8: 
_fin_resta_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
