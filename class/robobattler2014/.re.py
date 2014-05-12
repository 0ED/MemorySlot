# -*- coding: utf-8 -*-
import os
import sys
import re
srcdir = "Robos/"
source1 = sys.argv[1].replace(srcdir,"")
source2 = sys.argv[2].replace(srcdir,"")
name1 = source1.replace(".java","")
name2 =source2.replace(".java","")

sources = os.listdir(srcdir)
if not source1 in sources:
	print source1 + "がありません。"
	sys.exit(1)
if source2 in sources:
	print source2 + "は既にありますよ。"
	sys.exit(2)

with open(srcdir + source1, 'r') as a_file:
	aString = a_file.read()
	aString = aString.replace(name1, name2)
with open(srcdir + source2, 'w') as a_file:
	a_file.write(aString)
