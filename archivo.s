.data
_result:	.word	0

.text
.globl main

main: 

_principal_s0: 
li $t0, 2
move $a0, $t0
li $t0, 2
move $a1, $t0
jal _prueba_s0
sw $v0, _result
L0: 
li $v0, 1
lw $a0, _result
syscall
L1: 
li $v0, 10
syscall
_prueba_s0: 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
move $fp, $sp 
sw $s0 , -12($sp)
move $s0, $a0
sw $s1 , -16($sp)
move $s1, $a1
sub $sp, $sp, 20
move $t0, $s0
move $a0, $t0
move $t0, $s1
move $a1, $t0
jal _suma_s0s1
sw $v0, -20($fp)
L2: 
lw $t0, -20($fp)
move $v0, $t0
b _fin_prueba_s0
L3: 
_fin_prueba_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
_suma_s0s1: 
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
add $t2, $t0, $t1
sw $t2, -20($fp)
L4: 
lw $t0, -20($fp)
move $v0, $t0
b _fin_suma_s0s1
L5: 
_fin_suma_s0s1:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
