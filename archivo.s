.data
_resultado:	.word	0

.text
.globl main

main: 

_principal_s0: 
li $t0, 2
move $a0, $t0
li $t0, 2
move $a1, $t0
jal _suma_s0
sw $v0, _resultado
L0: 
li $v0, 1
lw $a0, _resultado
syscall
L1: 
li $v0, 10
syscall
_suma_s0: 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
move $fp, $sp 
sw $s0 , -12($sp)
move $s0, $a0
sw $s1 , -16($sp)
move $s1, $a1
sub $sp, $sp, 20
move $v0, null
b _fin_suma_s0
L2: 
_fin_suma_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
