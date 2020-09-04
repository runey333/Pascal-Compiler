	.data
newln:
	.asciiz "\n"
varx:
	.word 0
vary:
	.word 0
varcount:
	.word 0
	.text
main:
	li $v0 2
	la $t0, varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	#push $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 2
	#pop $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t0, vary
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	#push $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vary
	lw $v0 ($t0)
	#pop $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t0, varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	#push $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vary
	lw $v0 ($t0)
	#pop $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $t0 $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	la $t0 varx
	lw $v0 ($t0)
	move $t1 $v0
	la $t0 vary
	lw $v0 ($t0)
	#if less or equal, go to endif1
	ble $t1 $v0 endif1
	la $t0 varx
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	la $t0 vary
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
endif1:
	li $v0 14
	move $t1 $v0
	li $v0 14
	#if not equal, go to endif2
	bne $t1 $v0 endif2
	li $v0 14
	move $t1 $v0
	li $v0 14
	#if equal, go to endif3
	beq $t1 $v0 endif3
	li $v0 3
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
endif3:
	li $v0 14
	move $t1 $v0
	li $v0 14
	#if greater, go to endif4
	bgt $t1 $v0 endif4
	li $v0 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
endif4:
endif2:
	li $v0 15
	move $t1 $v0
	li $v0 14
	#if less or equal, go to endif5
	ble $t1 $v0 endif5
	li $v0 5
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
endif5:
	li $v0 1
	la $t0, varcount
	sw $v0 ($t0)
loop1:
	la $t0 varcount
	lw $v0 ($t0)
	move $t1 $v0
	li $v0 15
	#if greater, go to endloop1
	bgt $t1 $v0 endloop1
	la $t0 varcount
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newln
	syscall
	la $t0 varcount
	lw $v0 ($t0)
	#push $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 1
	#pop $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t0, varcount
	sw $v0 ($t0)
	j loop1
endloop1:
	li $v0 10
	syscall
