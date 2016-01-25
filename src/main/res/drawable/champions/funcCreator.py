with open("champions.txt", 'r+') as f, open("functions.txt", 'r+') as g:
	for line in f:
		a = line
		a = a[:1].upper() + a[1:]
		z = "championtoidmapping.getData().get"+a+"()."
		b = "mypreferences.savePreference("+z+"getName()"+","+z+"getId(), context);"
		b.replace(" ", "")
		g.write(b)

# mypreferences.savePreference(player.getName(),player.getId().intValue(), context);