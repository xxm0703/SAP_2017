import random

import math
from operator import attrgetter

from graphics import *


class Rocket:
    def __init__(self, point, tick, vel_list=None):
        self.finished = False
        self.point = point
        self.x = point.x
        self.y = point.y
        if vel_list is None:
            self.vel_list = []
        else:
            self.vel_list = vel_list[:]
        self.rect = Circle(self.point, 5)
        self.fitness = 0
        while self.vel_list is None or len(self.vel_list) < tick:
            self.vel_list.append(Point(random.randrange(-10, 10), random.randrange(-10, 10)))

    def draw(self):
        self.rect.setWidth(0)
        self.rect.setFill('red')
        self.rect.draw(win)

    def move(self, i):
        self.rect.move(self.vel_list[i].x, self.vel_list[i].y)
        self.point = self.rect.getP1()
        self.x = self.point.x
        self.y = self.point.y

    def __gt__(self, other):
        return self.fitness > other.fitness


class Population:
    def __init__(self, count, ticks, target):
        self.rockets = []
        self.count = count
        self.tick = ticks
        self.target = target
        tr = Circle(target, 8)
        tr.setFill('green')
        tr.draw(win)
        for x in range(count):
            self.rockets.append(Rocket(Point(win.width / 2, win.height), self.tick))

    def move_pop(self):
        for roc in self.rockets:
            roc.rect.draw(win)
        for i in range(self.tick):
            for rocket in self.rockets:
                if rocket.point.x == self.target.x and rocket.point.y == self.target.y:
                    rocket.finished = True
                elif not rocket.finished:
                    rocket.move(i)
            time.sleep(0.02)
        for roc in self.rockets:
            roc.rect.undraw()

    def calculate(self):
        sum = 0
        for rocket in self.rockets:
            x = abs(rocket.x - self.target.x)
            y = abs(rocket.y - self.target.y)
            rocket.fitness = 100 / math.sqrt(x ** 2 + y ** 2)
            if rocket.finished:
                rocket.fitness *= 1.4
            sum += rocket.fitness
        print(sum / self.count)

    def crossover(self):
        new_popul = []
        for x in range(self.count):
            par1 = max(self.get_rand(), key=attrgetter('fitness'))
            par2 = max(self.get_rand(), key=attrgetter('fitness'))
            temp = []
            for j in range(self.tick):
                if random.randint(0, 11) < 1:
                    temp.append(Point(random.randrange(-10, 10), random.randrange(-10, 10)))
                elif 6 > random.randint(0, 11) >= 1:
                    temp.append(par1.vel_list[j])
                else:
                    temp.append(par2.vel_list[j])

            new_rock = Rocket(Point(win.width/2, win.height), self.tick, temp)
            new_popul.append(new_rock)
        self.rockets = new_popul

    def get_rand(self):
        parents = []
        for i in range(5):
            parents.append(self.rockets[random.randrange(0, self.count)])
        return parents


win = GraphWin("First Graph", 400, 400)
win.setBackground('gray')
win.focus_set()
ticks = 100
popul = Population(20, ticks, Point(win.width / 2, 50))
while True:
    popul.move_pop()
    popul.calculate()
    popul.crossover()
# win.getKey()
# win.close()
