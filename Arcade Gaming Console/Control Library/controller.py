import RPi.GPIO as GPIO
	'''red 4 down 10 up 15 green 17 yellow 14 right 9 left 22 blue 11
	'''
def init():
	controller=arcadeController(15,10,9,22,11,14,,17,4)
def init(jUp,jDown,jRight,jLeft,bBlue,bYellow,bGreen,bRed):
	controller=arcadeController(jUp,jDown,jRight,jLeft,bBlue,bYellow,bGreen,bRed)
class arcadeController:
	def __init__(jUp,jDown,jRight,jLeft,bBlue,bYellow,bGreen,bRed):
		self.jUp,self.jDown,self.jRight,self.jLeft=jUp,jDown,jLeft,jRight
		self.bYellow,self.bRed,self.bGreen,self.bBlue=bYellow,bRed,bGreen,bBlue
		GPIO.setmode(GPIO.BCM)
		GPIO.setup(jUp, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(jDown, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(jLeft, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(jRight, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(bRed, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(bYellow, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(bBlue, GPIO.IN, pull_up_down = GPIO.PUD_UP)
		GPIO.setup(bGreen, GPIO.IN, pull_up_down = GPIO.PUD_UP)
	def up(self):
		return not GPIO.input(self.jUp)
	def down(self):
		return not GPIO.input(self.jDown)
	def right(self):
		return not GPIO.input(self.jRight)
	def left(self):
		return not GPIO.input(self.jLeft)
	def yellow(self):
		return not GPIO.input(self.bYellow)
	def red(self):
		return not GPIO.input(self.bRed)
	def blue(self):
		return not GPIO.input(self.bBlue)
	def green(self):
		return not GPIO.input(self.bGreen)