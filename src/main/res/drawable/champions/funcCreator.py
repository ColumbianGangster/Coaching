with open("champions.txt", 'r+') as f, open("functionsReverse.txt", 'r+') as g:
	for line in f:
		a = line
		a = a[:1].upper() + a[1:]
		z = "championtoidmapping.getData().get"+a+"()."
		b = "mypreferences.savePreference("+"Integer.toString("+z+"getId())"+","+z+"getName(), context);"
		b.replace(" ", "")
		g.write(b)

# mypreferences.savePreference(player.getName(),player.getId().intValue(), context);