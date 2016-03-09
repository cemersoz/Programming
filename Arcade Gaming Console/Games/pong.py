#!/usr/bin/env python
#Hisar Pong
#by Cem Ersoz
#Hisar Scho
#___________________________________________________
#imports:
import time
import pygame, serial
from pygame.locals import *
import RPi.GPIO as GPIO
from sys import exit
import random

sys.path.insert(0,"/home/pi/Desktop/HisArcade/pins")
import gamePins

gamePins.gameSetup()

pygame.init()

screen=pygame.display.set_mode((1280,768),pygame.FULLSCREEN)
pygame.display.set_caption("HISAR PONG!")

pygame.font.init()
fontSmall = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",30)
red, yellow, green, blue=(235,53,47),(235,230,45),(0,185,10),(73,170,235)
directUp = fontSmall.render("up", True,(255,255,255))
directDown = fontSmall.render("down", True,(255,255,255))
directLeft = fontSmall.render("left", True,(255,255,255))
directRight = fontSmall.render("right", True,(255,255,255))
directExit = fontSmall.render("exit", True,(255,255,255))

#creating the background.
back = pygame.Surface((1280,768))
background = back.convert()
background.fill((0,0,0))
pong1_move,pong2_move=0,0
# class definitions
class Pong:
    def __init__(self,a,b,c):
        self.initx,self.inity=a,b
        self.x,self.y,self.speed=a,b,c
        self.score=0
        self.movesUp,self.movesDown=False,False
    def move(self):
        self.y+=self.speed
    def moveDown(self):
        if self.y<705-2*self.speed:
            self.y+=self.speed
        elif self.y<705-self.speed:
            self.y=705-self.speed
    def initialize(self):
        self.x,self.y=self.initx,self.inity
class Ball:
    def __init__(self,a,b,c):
        self.initx,self.inity=a,b
        self.x,self.y,self.xspeed,self.yspeed=a,b,c,c
    def move(self):
        self.y+=self.yspeed
        self.x+=self.xspeed
        if self.y<12.5 or self.y>697.5:
            self.yspeed=-self.yspeed
        if self.x<pong1.x:
            pong2.score+=1
            self.xspeed=-self.xspeed
            pong2.initialize()
            pong1.initialize()
            self.x,self.y=self.initx,self.inity
        elif self.x>pong2.x:
            pong1.score+=1
            self.xspeed=-self.xspeed
            pong1.initialize()
            pong2.initialize()
            self.x,self.y=self.initx,self.inity
        elif self.x<pong1.x+17.5 and self.y+7.5>pong1.y and self.y-7.5<pong1.y+100:
            self.xspeed=-self.xspeed
            if pong1.movesUp:
                if self.yspeed>0:
                    self.yspeed+=self.yspeed/2
                else:
                    self.yspeed-=self.yspeed/2
            elif pong1.movesDown:
                if self.yspeed<0:
                    self.yspeed+=self.yspeed/2
                else:
                    self.yspeed-=self.yspeed/2
        elif self.x>pong2.x-7.5 and self.y+5>pong2.y and self.y-5<pong2.y+100:
            self.xspeed=-self.xspeed
            if pong2.movesUp:
                if self.yspeed>0:
                    self.yspeed+=self.yspeed/2
                else:
                    self.yspeed-=self.yspeed/2
            elif pong2.movesDown:
                if self.yspeed<0:
                    self.yspeed+=self.yspeed/2
                else:
                    self.yspeed-=self.yspeed/2
#game methods
def checkKeys():
    for event in pygame.event.get():
        if event.type == QUIT:
            exit()
        if event.type == KEYDOWN:
            if event.key == K_UP:
                pong1.speed = -8.
                pong2.speed = -8.
                pong1.movesDown=True
                pong2.movesDown=True
            elif event.key == K_DOWN:
                pong1.speed = 8.
                pong2.speed = 8.
                pong1.movesDown=True
                pong2.movesDown=True
        elif event.type == KEYUP:
            if event.key == K_UP:
                pong1.speed = 0.
                pong2.speed = 0.
                pong1.movesUp=False
                pong2.movesUp=False
            elif event.key == K_DOWN:
                pong1.speed = 0.
                pong2.speed = 0.
                pong1.movesDown=False
                pong2.movesDown=False

#clock and font objects
font = pygame.font.SysFont("Fonts/ARCADECLASSIC.TTF",80)
#serial_port = '/dev/ttyACM0'
pong1,pong2=Pong(215,300,0),Pong(1065,300,0)
ball1=Ball(440,353,2)
circ_sur = pygame.Surface((15,15))
circ = pygame.draw.circle(circ_sur,(0,255,0),(15/2,15/2),15/2)
circle = circ_sur.convert()
while True:
    if not GPIO.input(gamePins.red):
	execfile('launchGPIO.py')
    for event in pygame.event.get():
        if event.type == KEYDOWN:
	    if event.key == K_q:
	        exit()
    if not GPIO.input(gamePins.up):
      pong1.speed=-6.
    elif not GPIO.input(gamePins.down):
      pong1.speed=6.
    else:
      pong1.speed=0.
    if not GPIO.input(gamePins.yellow):
      pong2.speed=-6.
    elif not GPIO.input(gamePins.green):
      pong2.speed=6.
    else:
      pong2.speed=0.
    if pong1.score<4 and pong2.score<4:
	screen.blit(background,(0,0))
	screen.blit(directUp,(90,300))
	screen.blit(directDown,(90,380))
	screen.blit(directUp,(1190,300))
	screen.blit(directDown,(1190,380))
	screen.blit(directExit,(1190,660))
	pygame.draw.polygon(screen,(225,240,229),[[20,330],[60,330],[40,295]],0)
	pygame.draw.polygon(screen,(225,240,229),[[20,375],[60,375],[40,410]],0)
	pygame.draw.circle(screen, (red), (1140,680),20,0) 
	pygame.draw.circle(screen, (yellow), (1140,320),20,0) 
	pygame.draw.circle(screen, (green), (1140,400),20,0) 
        score1 = font.render(str(pong1.score), True,(255,255,255))
        score2 = font.render(str(pong2.score), True,(255,255,255))
        
        screen.blit(score1,(578.,20.))
        screen.blit(score2,(703.,20.))
        frame = pygame.draw.rect(screen,(255,255,255),Rect((205,5),(875,700)),2)
        middle_line = pygame.draw.line(screen,(255,255,255),(640,5),(640,700))
        pygame.draw.rect(screen,(255,255,255),Rect((pong1.x,pong1.y),(10,100)))
        pygame.draw.rect(screen,(255,255,255),Rect((pong2.x,pong2.y),(10,100)))
        pygame.draw.rect(screen,(255,255,255),Rect((ball1.x-7.5,ball1.y-7.5),(15,15)))

        pong1.move()
        pong2.move()
        ball1.move()
    elif not GPIO.input(gamePins.red) and (pong1.score>4 or pong2.score>4):
        execfile("launchGPIO.py")
    elif pong1.score>4:
        gameOver(1)
    elif pong2.score>4:
        gameOver(2)
    pygame.display.update()
def gameOver(a):
    if not GPIO.input(gamePins.red):
      pong1.score=0
      pong2.score=0
      execfile(launchGPIO.py)
    if a==1:
        score = font.render("Player one wins!", True,(255,255,255))
        screen.blit(score,(350.,50.))
        screen.blit(score1,(450.,200.))
        screen.blit(score2,(575.,200.))
    elif a==2:
        score = font.render("Player two wins!", True,(255,255,255))
        screen.blit(score,(350.,50.))
        screen.blit(score1,(450.,200.))
        screen.blit(score2,(575.,200.))
