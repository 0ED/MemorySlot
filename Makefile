#
# Repository
# https://bitbucket.org/LightFox
#
# Git tutorial
# http://web-k.github.io/blog/2012/11/08/git/ 
#

COMMENT = "init"

all:
	git add .
push:
	git commit -m ${COMMENT} 
	git push origin master
status:
	git status
reset:
	git reset --soft HEAD~
resets:
	git reset --hard commit_hash
	
