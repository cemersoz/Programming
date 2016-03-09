#!/usr/bin/env python
#Hisar Snake
#copyright: Cem Ersoz
#Hisar Schools
import sys
import time
import pygame, serial
import RPi.GPIO as GPIO
from pygame.locals import *
import random
pygame.init()

sys.path.insert(0,"/home/pi/Desktop/HisArcade/pins")
import gamePins

gamePins.gameSetup()

screen=pygame.display.set_mode((1024,718),pygame.FULLSCREEN)
pygame.display.set_caption("Hisar Snake!")
#Creating 4 boxes and Background.
back = pygame.Surface((1024,718))
pygame.font.init()
fontSmall = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",30)
background = back.convert()
background.fill((0,0,0))
left,down,right,up=1,2,3,4
red, yellow, green, blue=(235,53,47),(235,230,45),(0,185,10),(73,170,235)
directUp = fontSmall.render("up", True,(255,255,255))
directDown = fontSmall.render("down", True,(255,255,255))
directLeft = fontSmall.render("left", True,(255,255,255))
directRight = fontSmall.render("right", True,(255,255,255))
directExit = fontSmall.render("exit", True,(255,255,255))
#GPIO Pins Def
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
def move():
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
def bg():#draws the background
    screen.blit(background,(0,0))
    text2 = font.render("Score  "+str(Score), True,(255,255,255))
    text5 = font.render("Level  "+str(Score/500), True,(255,255,255))
    screen.blit(background,(0,0))
    pygame.draw.rect(screen,(255,255,255),Rect((100,150),(640,480)),2)
    screen.blit(text1,(250.,30.))
    screen.blit(text2,(775.,150.))
    screen.blit(text3,(450.,680.))
    screen.blit(text5,(775.,250.))
     

    screen.blit(directDown,(840,340))
    screen.blit(directUp,(840,420))
    screen.blit(directRight,(840,500))
    screen.blit(directLeft,(840,580))

    pygame.draw.polygon(screen,(225,240,229),[[775,335],[815,335],[795,370]],0)
    pygame.draw.polygon(screen,(225,240,229),[[775,450],[815,450],[795,415]],0)
    pygame.draw.polygon(screen,(225,240,229),[[775,495],[775,540],[815,518]],0)
    pygame.draw.polygon(screen,(225,240,229),[[815,575],[815,620],[775,598]],0)
    pygame.draw.circle(screen, (red), (795,680),20,0) 
    screen.blit(directExit,(840,660))
#clock and font objects
clock = pygame.time.Clock()
all_fonts = pygame.font.get_fonts()
font = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",40)
#boxes and shapes:
#fonts and texts
font1 = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",60)
font2 = pygame.font.Font("Fonts/ARCADECLASSIC.TTF",30)
font3 = pygame.font.Font("Fonts/ka1.ttf", 60)
text1 = font3.render("HISAR SNAKE", True,(255,255,255))
text3 = font2.render("by    Cem  Ersoz", True,(255,255,255))
text4 = font.render("GAME OVER", True,(255,255,255))
text5 = font.render("YOU WIN", True,(255,255,255))
def canMove():
    if snake.mov_dir==right:
        return snake.x<19 and (not Grid[snake.x+1][snake.y]==1 or (snake.Arr[len(snake.Arr)-1][0]==snake.x+1 and snake.Arr[len(snake.Arr)-1][1]==snake.y))
    elif snake.mov_dir==left:
        return snake.x>0 and (not Grid[snake.x-1][snake.y]==1 or (snake.Arr[len(snake.Arr)-1][0]==snake.x-1 and snake.Arr[len(snake.Arr)-1][1]==snake.y))
    elif snake.mov_dir==up:
        return snake.y>0 and (not Grid[snake.x][snake.y-1]==1 or (snake.Arr[len(snake.Arr)-1][0]==snake.x and snake.Arr[len(snake.Arr)-1][1]==snake.y-1))
    elif snake.mov_dir==down:
        return snake.y<14 and (not Grid[snake.x][snake.y+1]==1 or (snake.Arr[len(snake.Arr)-1][0]==snake.x and snake.Arr[len(snake.Arr)-1][1]==snake.y+1))
def gameOver():
    text2 = font.render("Score  "+str(Score), True,(255,255,255))
    screen.blit(background,(0,0))
    screen.blit(text1,(280.,100.))
    if(snake.length<300):
        screen.blit(text4,(450.,250.))
    else:
        screen.blit(text5,(500.,250.))
    pygame.draw.circle(screen, (red), (470,450),20,0) 
    screen.blit(directExit,(520,445))
    screen.blit(text2,(450.,350.))
    screen.blit(text3,(440.,670.))
def clearGrid():
    for x in xrange(20):
        for y in xrange(15):
            Grid[x][y]=0
old_time=0
Score=0
doAll=True
initial_time=time.time()
snake=Snake()
bait=Bait()
while True:
    if not GPIO.input(gamePins.red):
	execfile('launchGPIO.py')
    for event in pygame.event.get():
        if event.type == KEYDOWN:
	    if event.key == K_q:
	        exit()
    if doAll:
	if not buttonChanged:
	  if not GPIO.input(gamePins.up) and not snake.mov_dir==down:
	    snake.mov_dir=up
	  elif not GPIO.input(gamePins.down) and not snake.mov_dir==up:
	    snake.mov_dir=down
	  elif not GPIO.input(gamePins.left) and not snake.mov_dir==right:
	    snake.mov_dir=left
	  elif not GPIO.input(gamePins.right) and not snake.mov_dir==left:
	    snake.mov_dir=right

        bg()
        checkSnake()
        level=Score/500
        multiplier=(level+6)/3+1
        current_time=int((time.time()-initial_time)*multiplier)
        if current_time>old_time:
            if canMove():
                move()
                buttonChanged=False
                bait.checkBait()
                old_time=current_time
            else:
                doAll= False
    elif not doAll:
        gameOver()
        if not GPIO.input(gamePins.red):
            clearGrid()
            doAll=True
            time.sleep(1)
            execfile("launchGPIO.py")
    pygame.display.update()
    
