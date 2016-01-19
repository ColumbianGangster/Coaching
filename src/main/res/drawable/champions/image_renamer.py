import glob, os

def rename(dir, pattern, titlePattern):
	i=0
	for pathAndFilename in glob.iglob(os.path.join(dir, pattern)):
		title, ext = os.path.splitext(os.path.basename(pathAndFilename))
		os.rename(pathAndFilename, os.path.join(dir, titlePattern % str(i) + title + ext))
		i+=1

rename(os.getcwd(), r'*.png', r'd%s')
rename(os.getcwd(), r'*.gif', r'd%s')