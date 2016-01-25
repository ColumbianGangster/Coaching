from os import listdir
import os
from os.path import isfile, join
import fileinput

onlyfiles = [f for f in listdir(os.getcwd()) if isfile(join(os.getcwd(), f))]
onlyfiles.remove("fixer.py")
for f in onlyfiles:
	for line in fileinput.input(f, inplace=True):
			print(line.replace("import org.apache.commons.lang.builder.EqualsBuilder;", "import org.apache.commons.lang3.builder.EqualsBuilder;"))
			print(line.replace("import org.apache.commons.lang.builder.HashCodeBuilder;", "import org.apache.commons.lang3.builder.HashCodeBuilder;"))
			print(line.replace("import org.apache.commons.lang.builder.ToStringBuilder;", "import org.apache.commons.lang3.builder.ToStringBuilder;"))
			print(line.replace("@Generated(\"org.jsonschema2pojo\")", ""))