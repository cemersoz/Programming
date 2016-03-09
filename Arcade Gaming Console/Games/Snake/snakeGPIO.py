#!/usr/bin/env python
#Hisar Snake
#copyright: Cem Ersoz
#Hisar Schools
import time
import pygame, serial
import RPi.GPIO as GPIO
from pygame.locals import *
from sys import exit
from serial import Serial
import random
#GPIO Pins Def

GPIO.setmode(GPIO.BCM);
GPIO.setup(3, GPIO.IN);
GPIO.setup(5, GPIO.IN);

pygame.init()
screen=pygame.display.set_mode((1024,718),0,32)
pygame.display.set_caption("Hisar Snake!")
#Creating 4 boxes and Background.
back = pygame.Surface((1024,718))
background = back.convert()
background.fill((0,0,0))
left,down,right,up=1,2,3,4
Score=0
time_sec=0
time_sec_new=0
buttonChanged=False
Grid = [[0 for x in xrange(15)]for x in xrange(20)]
class Snake():
    def __init__(self):
        self.x,self.y,self.mov_dir,self.length=2,5,right,3
        self.Arr=[[2,5]]
class Bait():
    def __init__(self):
        self.x,self.y=7,8#random.randint(3,19),(0,9)
    def checkBait(self):
        global Score
        if self.x==snake.Arr[0][0] and self.y==snake.Arr[0][1]:
            snake.length+=1
            Score+=50
            self.newBait()
    def newBait(self):
        self.ranx,self.rany=random.randint(0,19),random.randint(0,14)
        while Grid[self.ranx][self.rany]==1:
            self.ranx,self.rany=random.randint(0,19),random.randint(0,14)
        self.x,self.y=self.ranx,self.rany
def checkSnake():
    for x in xrange(len(snake.Arr)):
        if x==0:
            pygame.draw.rect(screen,(255,0,252),Rect((103+32*snake.Arr[x][0],153+32*snake.Arr[x][1]),(26,26)))
        elif x<snake.length:
            pygame.draw.rect(screen,(180,255,252),Rect((103+32*snake.Arr[x][0],153+32*snake.Arr[x][1]),(26,26)))#purple
            pygame.draw.rect(screen,(255,100,252),Rect((108+32*bait.x,158+32*bait.y),(16,16)))
        else:
            Grid[snake.Arr[x][0]][snake.Arr[x][1]]=0
    snake.Arr=snake.Arr[0:snake.length]
def checkButtons():
    global buttonChanged
    print(GPIO.input(3))
    if not GPIO.input(3):
        buttonChanged = True
        if snake.mov_dir==right:
            snake.mov_dir = down
        elif snake.mov_dir==down:
            snake.mov_dir = left
        elif snake.mov_dir==left:
            snake.mov_dir = up
        elif snake.mov_dir==up:
            snake.mov_dir = right
    elif not GPIO.input(5):
        buttonChanged = True
        if snake.mov_dir==right:
            snake.mov_dir = up
        elif snake.mov_dir==up:
            snake.mov_dir = left
        elif snake.mov_dir==left:
            snake.mov_dir = down
        elif snake.mov_dir==down:
            snake.mov_dir = right
def move():
    global buttonChanged
    if snake.mov_dir==right:
        snake.x+=1
    elif snake.mov_dir==left:
        snake.x-=1
    elif snake.mov_dir==up:
        snake.y-=1
    elif snake.mov_dir==down:
        snake.y+=1
    snake.Arr.insert(0,[snake.x,snake.y])
    Grid[snake.x][snake.y]=1
    buttonChanged=False
def bg():#draws the background
    text2 = font.render("Score  "+str(Score), True,(255,255,255))
    text5 = font.render("Level  "+str(Score/500), True,(255,255,255))
    screen.blit(background,(0,0))
    pygame.draw.rect(screen,(255,255,255),Rect((100,150),(640,480)),2)
    screen.blit(text1,(250.,30.))
    screen.blit(text2,(775.,150.))
    screen.blit(text3,(450.,680.))
    screen.blit(text5,(775.,250.))
#clock and font objects
clock = pygame.time.Clock()
all_fonts = pygame.font.get_fonts()
font = pygame.font.Font("ARCADECLASSIC.TTF",60)
#serial_port = '/dev/ttyACM0'
#boxes and shapes:
#fonts and texts
font1 = pygame.font.Font("ARCADECLASSIC.TTF",60)
font2 = pygame.font.Font("ARCADECLASSIC.TTF",30)
font3 = pygame.font.Font("ka1.ttf", 60)
text1 = font3.render("HISAR SNAKE", True,(255,255,255))
text3 = font2.render("by    Cem  Ersoz", True,(255,255,255))
text4 = font.render("GAME OVER", True,(255,255,255))
text5 = font.render("YOU WIN", True,(255,255,255))
cShape=0
def canMove():
    if snake.mov_dir==right:
        return snake.x<19 and not Grid[snake.x+1][snake.y]==1
    elif snake.mov_dir==left:
        return snake.x>0 and not Grid[snake.x-1][snake.y]==1
    elif snake.mov_dir==up:
        return snake.y>0 and not Grid[snake.x][snake.y-1]==1
    elif snake.mov_dir==down:
        return snake.y<14 and not Grid[snake.x][snake.y+1]==1
def gameOver():        
    text2 = font.render("Score  "+str(Score), True,(255,255,255))
    screen.blit(background,(0,0))
    screen.blit(text1,(350.,100.))
    if(snake.length<300):
        screen.blit(text4,(350.,250.))
    else:
        screen.blit(text5,(350.,250.))
    screen.blit(text2,(350.,350.))
    screen.blit(text3,(350.,670.))
old_time=0
Score=0
doAll=True
initial_time=time.time()
snake=Snake()
bait=Bait()
#ser = Serial('/dev/ttyACM0', 9600)
while True:
    '''
    button1 = ser.readline()
    print(button1)
    if button1[0:1]=="L":
        if snake.mov_dir==left:
            snake.mov_dir=up
            buttonChanged=True
        elif snake.mov_dir==up:
            snake.mov_dir=right
            buttonChanged=True
        elif snake.mov_dir==right:
            snake.mov_dir=down
            buttonChanged=True
        elif snake.mov_dir==down:
            snake.mov_dir=left
            buttonChanged=True
    elif button1[0:1]=="R":
        if snake.mov_dir==left:
            snake.mov_dir=down
            buttonChanged=True
        elif snake.mov_dir==up:
            snake.mov_dir=left
            buttonChanged=True
        elif snake.mov_dir==right:
            snake.mov_dir=up
            buttonChanged=True
        elif snake.mov_dir==down:
            snake.mov_dir=right
            buttonChanged=True
            '''
    while doAll:
        bg()
        checkSnake()
        checkButtons()
        level=Score/500
        multiplier=(level+6)/3+1
        current_time=int((time.time()-initial_time)*multiplier)
        if current_time>old_time:
            if canMove():
                move()
                bait.checkBait()
                old_time=current_time
            else:
                doAll= False
        pygame.display.update()
    while not doAll:
            gameOver()
            for event in pygame.event.get():
                if event.type == QUIT:
                    exit()
                elif event.type == KEYDOWN:
                    if event.key == K_UP:
                        clearGrid()
                        doAll=True
            pygame.display.update()
