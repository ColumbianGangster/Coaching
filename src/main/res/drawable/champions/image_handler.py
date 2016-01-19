f = open('output.txt', 'r+')
i = 0
for line in f:
	line = "d"+str(i)+line
	i+=1
	f.write(line)