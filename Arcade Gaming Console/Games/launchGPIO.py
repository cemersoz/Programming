#!/usr/bin/env python
#Hisar Snake
#copyright: Memet
#Hisar Schools
import time, sys, os
import pygame
from pygame.locals import *
import RPi.GPIO as GPIO

os.chdir("/home/pi/Desktop/HisArcade/")
games2 = os.listdir("/home/pi/Desktop/HisArcade/")
print games2
games2 = [elem[0:-3] for elem in games2 if elem[-3:]=='.py' and elem[0] != '.' and elem != 'launchGPIO.py']
games2.sort()



sys.path.insert(0,"/home/pi/Desktop/HisArcade/pins")
import gamePins

gamePins.gameSetup()

pygame.init()
screen=pygame.display.set_mode((1024,718), pygame.FULLSCREEN)
pygame.display.set_caption("HisArcade Launcher!")
#creating the background.
back = pygame.Surface((1024,718))
background = back.convert()
background.fill((0,0,0))
font = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",50)
fontSmall = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",30)

selected = 0

text1 = font.render("Tetris", True,(255,255,255))
text2 = font.render("Pong 2p", True,(255,255,255))
text3 = font.render("Snake", True,(255,255,255))
text4 = font.render("Pacman", True,(255,255,255)) 

def setUpTexts():
   global text1
   global text2
   global text3
   global text4
   text1 = font.render(games2[selected%len(games2)], True,(255,255,255))
   text2 = font.render(games2[(selected+1)%len(games2)], True,(255,255,255))
   text3 = font.render(games2[(selected+2)%len(games2)], True,(255,255,255))
   text4 = font.render(games2[(selected+3)%len(games2)], True,(255,255,255)) 


facebook = pygame.image.load('Home/facebook.png')
twitter = pygame.image.load('Home/twitter.png')
insta = pygame.image.load('Home/instagram.png')

setUpTexts()

red, yellow, green, blue=(235,53,47),(235,230,45),(0,185,10),(73,170,235)
directUp = fontSmall.render("up", True,(255,255,255))
directDown = fontSmall.render("down", True,(255,255,255))
directSelect = fontSmall.render("select", True,(255,255,255))
media = font.render("HisarCS", True,(255,255,255))
toBlit = True

while True:
    for event in pygame.event.get():
        if event.type == KEYDOWN:
	    if event.key == K_q:
	        exit()
	    if event.key == K_s:
		selected += 1
		setUpTexts()
	    if event.key == K_w:
		selected -= 1
		setUpTexts()
	    if event.key == K_a:
		execfile(games2[(selected+1) % len(games2)]+'.py')
    up = GPIO.input(gamePins.up)
    down = GPIO.input(gamePins.down)
    select = GPIO.input(gamePins.green)
    screen.blit(background,(0,0))
    screen.blit(text1,(450.,100.))
    if toBlit:
	screen.blit(text2,(450.,250.))
	toBlit=False
    else:
	toBlit=True
    screen.blit(text3,(450.,400.))
    screen.blit(text4,(450.,550))
    screen.blit(media,(55.,300))
    screen.blit(directDown,(920,340))
    screen.blit(directUp,(920,260))
    screen.blit(directSelect,(920,420))
    screen.blit(facebook, (45,220))
    screen.blit(twitter, (115,220))
    screen.blit(insta, (185,220))
    if not down:
	selected += 1
	setUpTexts()
    elif not up:
	selected -= 1
	setUpTexts()
    elif not select:
	execfile(games2[(selected+1) % len(games2)]+'.py')
    time.sleep(.1)
    pygame.draw.polygon(screen,(225,240,229),[[840,335],[880,335],[860,370]],0)
    pygame.draw.polygon(screen,(225,240,229),[[840,290],[880,290],[860,255]],0)
    pygame.draw.circle(screen, (green), (860,435),20,0) 
    pygame.draw.polygon(screen, (255,255,255), [[385,110+150], [400,125+150],[385,140+150],5])

    pygame.display.update()
