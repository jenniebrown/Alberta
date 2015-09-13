import cPickle
from data import *

products = {}
sales = {}
#user = 3  # 1 admin 2 manager 3 cashier

def startup():
    def load(name):
        try:
            globals()[name] = cPickle.load(open(name, 'r'))
        except IOError:
            pass

    load('products')
    load('sales')

    global user; user = input('Auth user: ')


def shutdown():
    def dump(name): cPickle.dump(eval(name), open(name, 'w'))

    dump('products')
    dump('sales')

startup()

if user == 1:
    while True:
        choice = input('1. Update products\n2. Display products\n3. Quit\n')
        if   choice == 1: products.update(Product.load())
        elif choice == 2: #sales.update(Sale.load())
            for i in products.itervalues():
                print i
        else: break

shutdown()
