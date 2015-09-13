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

def record_sale(sale):
    ''' Add **Sale** ``sale`` to ``sales`` dict '''
    sales[sale.time] = sale

startup()

if user == 1:
    while True:
        choice = input('1. Update products\n2. Display products\n3. Display sales\n4. Quit\n')
        if   choice == 1: products.update(Product.load())
        elif choice == 2:
            for i in products.itervalues():
                print i
        elif choice == 3:
            for i in sales.itervalues():
                print i
        else: break
else:
    upc = None
    print 'Process sale\n Enter 0 to quit'
    while True:
        upc = raw_input('Enter upc: ')
        if upc == '0': break
        try:
            product = products[upc]
        except KeyError:
            print 'Product does not exist.'
            continue

        cname = raw_input('Enter customer name: ')
        pmethod = raw_input('Enter payment method: ')
        comment = raw_input('If desired, enter comment: ')
        record_sale(Sale(product, cname, pmethod, comment if comment else None)) 

shutdown()
