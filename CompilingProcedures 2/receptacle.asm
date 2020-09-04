	.data
newln:
	.asciiz "\n"
varcount:
	.word 0
varignore:
	.word 0
vartimes:
	.word 0
	.text
main:
	li $v0 196
	la $t0, varcount
	sw $v0 ($t0)
	li $v0 0
	la $t0, vartimes
	sw $v0 ($t0)
	#push $ra
	subu $sp $sp 4
	sw $ra ($sp)
	li $v0 10
	move $a0 $v0
	#push $a0
	subu $sp $sp 4
	sw $a0 ($sp)
	li $v0 13
	move $a0 $v0
	#push $a0
	subu $sp $sp 4
	sw $a0 ($sp)
	jal procprintSquares
	#pop $a0
	lw $a0 ($sp)
	addu $sp $sp 4
	#pop $a0
	lw $a0 ($sp)
	addu $sp $sp 4
	#pop $ra
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0, varignore
	sw $v0 ($t0)
	la $t0 varcount
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	la $t0 vartimes
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	li $v0 10
	syscall
procprintSquares:
	move $a0 $zero
	#push $a0
	subu $sp $sp 4
	sw $a0 ($sp)
	move $a0 $zero
	#push $a0
	subu $sp $sp 4
	sw $a0 ($sp)
	move $a0 $zero
	#push $a0
	subu $sp $sp 4
	sw $a0 ($sp)
	lw $v0 16($sp)
	addu $t0 $sp 4
	sw $v0 ($t0)
loop1:
	lw $v0 4($sp)
	move $t5 $v0
	lw $v0 12($sp)
	move $t6 $v0
	#if greater, go to endloop1
	bgt $t5 $t6 endloop1
	lw $v0 4($sp)
	move $t7 $v0
	lw $v0 4($sp)
	move $t8 $v0
	mult $t7 $t8
	mflo $v0
	addu $t0 $sp 0
	sw $v0 ($t0)
	lw $v0 0($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	lw $v0 4($sp)
	move $t7 $v0
	li $v0 1
	move $t8 $v0
	addu $v0 $t7 $t8
	addu $t0 $sp 4
	sw $v0 ($t0)
	la $t0 vartimes
	lw $v0 ($t0)
	move $t7 $v0
	li $v0 1
	move $t8 $v0
	addu $v0 $t7 $t8
	la $t0, vartimes
	sw $v0 ($t0)
	j loop1
endloop1:
	#pop $a0
	lw $a0 ($sp)
	addu $sp $sp 4
	#pop $a0
	lw $a0 ($sp)
	addu $sp $sp 4
	#pop $a0
	lw $a0 ($sp)
	addu $sp $sp 4
	move $v0 $a0
	jr $ra
