.data
_x:	.word	0
_y:	.word	0
_msg0:	.asciiz	"el resultado es: "

.text
.globl main

null:li$t0,10
sw $t0, 10
null:li $v0, 4
la $a0, message0
syscall
null:li $v0, 1
lw $a0, _x
syscall
null: