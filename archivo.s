.data
_i:	.word	0
_x1:	.word	0
_msg0:	.asciiz	"hola"

.text
.globl main

main: 

_principal_s0: 
li $t0,1
li $t1, 10
add $t2, $t0, $t1
sw $t2, _i
L0: 
lw $t0, _i
li $t1, 10
add $t2, $t0, $t1
li $v0, 1
move $a0, $t2
syscall
L1: 
li $v0, 5
syscall
sw $v0, _i
L2: 
li $t0, 1
move $a0, $t0
li $t0, 2
move $a1, $t0
jal _suma_s0
sw $v0, _x1
L3: 
lw $t0, _x1
li $t1,12
bge $t0, $t1, L4
b L5
L4: 
li $v0, 4
la $a0, _msg0
syscall
L7: 
b L11
L5: 
lw $t0, _x1
li $t1,20
blt $t0, $t1, L8
b L10
L8: 
li $v0, 5
syscall
sw $v0, _i
L9: 
b L11
L10: 
L11: 
L13: 
lw $t0, _x1
li $t1,10
bne $t0, $t1, L12
b L17
L12: 
li $v0, 4
la $a0, _msg0
syscall
L14: 
lw $t0, _x1
li $t1,10
blt $t0, $t1, L15
b L16
L15: 
b L17
L16: 
b L13
L17: 
L18: 
b L18
L19: 
li $t0, 1
sw $t0, _x1
L20: 
lw $t0, _x1
li $t1,10
beq $t0, $t1, L21
b L24
L21: 
li $v0, 4
la $a0, _msg0
syscall
L23: 
L22: 
lw $t0, _x1
li $t1, 1
add $t2, $t0, $t1
sw $t2,_x1
b L20
L24: 
li $v0, 10
syscall
_suma_s0: 
move $fp, $sp 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
sw $s0 , -12($sp)
move $s0, $a0
sw $s1 , -16($sp)
move $s1, $a1
sub $sp, $sp, 20
move $v0, null
b _fin_suma_s0
L25: 
_fin_suma_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $s1 , -16($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
_algo_s0: 
move $fp, $sp 
sw $fp, -4($sp) 
sw $ra, -8($sp) 
sw $s2 , -12($sp)
move $s2, $a2
sub $sp, $sp, 16
li $t0, 20
sw $t0, -16($fp)
L26: 
li $v0, 1
lw $a0, _dundo
syscall
L27: 
_fin_algo_s0:
move $sp, $fp 
lw $s0 , -12($fp)
lw $ra, -8($fp) 
lw $fp, -4($fp) 
jr $ra
