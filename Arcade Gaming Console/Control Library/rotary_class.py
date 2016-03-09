#!/usr/bin/env python
#
# Raspberry Pi Rotary Encoder Class
# $Id: rotary_class.py,v 1.2 2014/01/31 13:34:48 bob Exp $
#
# Author : Bob Rathbone
# Site   : http://www.bobrathbone.com
#
# This class uses standard rotary encoder with push switch
# 
#

import RPi.GPIO as GPIO

class RotaryEncoder:

	CLOCKWISE=1
	ANTICLOCKWISE=2
	BUTTON1=3
	BUTTON2=4
	BUTTON3=5
	BUTTON5=6

	rotary_a = 0
	rotary_b = 0
	rotary_c = 0
	last_state = 0
	direction = 0

	# Initialise rotary encoder object
	def __init__(self,pinA,pinB,button1,button2,button3,callback):
		self.pinA = pinA
		self.pinB = pinB
		self.button1 = button1
		self.button2 =button2
		self.button3=button3
		self.callback = callback

		GPIO.setmode(GPIO.BCM)
		
		# The following lines enable the internal pull-up resistors
		# on version 2 (latest) boards		# For version 1 (old) boards comment out the above four lines
		# and un-comment the following 3 lines
		GPIO.setup(self.pinA, GPIO.IN)
		GPIO.setup(self.pinB, GPIO.IN)
		GPIO.setup(self.button1, GPIO.IN)
		GPIO.setup(self.button2, GPIO.IN)
		GPIO.setup(self.button3, GPIO.IN)

		# Add event detection to the GPIO inputs
		GPIO.add_event_detect(self.pinA, GPIO.FALLING, callback=self.switch_event)
		GPIO.add_event_detect(self.pinB, GPIO.FALLING, callback=self.switch_event)
		GPIO.add_event_detect(self.button1, GPIO.BOTH, callback=self.button_event1, bouncetime=200)
		GPIO.add_event_detect(self.button2, GPIO.BOTH, callback=self.button_event2, bouncetime=200)
		GPIO.add_event_detect(self.button3, GPIO.BOTH, callback=self.button_event3, bouncetime=200)
		return

	# Call back routine called by switch events
	def switch_event(self,switch):
		if GPIO.input(self.pinA):
			self.rotary_a = 1
		else:
			self.rotary_a = 0

		if GPIO.input(self.pinB):
			self.rotary_b = 1
		else:
			self.rotary_b = 0

		self.rotary_c = self.rotary_a ^ self.rotary_b
		new_state = self.rotary_a * 4 + self.rotary_b * 2 + self.rotary_c * 1
		delta = (new_state - self.last_state) % 4
		self.last_state = new_state
		event = 0

		if delta == 1:
			if self.direction == self.CLOCKWISE:
				# print "Clockwise"
				event = self.direction
			else:
				self.direction = self.CLOCKWISE
		elif delta == 3:
			if self.direction == self.ANTICLOCKWISE:
				# print "Anticlockwise"
				event = self.direction
			else:
				self.direction = self.ANTICLOCKWISE
		if event > 0:
			self.callback(event)
		return


	# Push button up event
	def button_event1(self,button1):
		if GPIO.input(button1): 
                        event = self.BUTTON1
                        self.callback(event)
		return
        def button_event2(self,button2):
		if GPIO.input(button2): 
			event = self.BUTTON2
                        self.callback(event)
		return
        def button_event3(self,button3):
		if GPIO.input(button3): 
			event = self.BUTTON3
		else:
                        event = self.BUTTON5
                self.callback(event)
		return

	# Get a switch state
	def getSwitchState(self, switch):
		return  GPIO.input(switch)

# End of RotaryEncoder class

