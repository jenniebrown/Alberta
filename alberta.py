import cPickle
from data import *

products = {} # Indexed by upc
sales = {} # Index by time

def startup():
    def load(name):
        try:
            globals()[name] = cPickle.load(open(name, 'r'))
        except IOError:
            pass

    load('products')
    load('sales')

    # 1 admin 2 manager 3 cashier
    global user; user = input('Auth user: ')


def shutdown():
    def dump(name): cPickle.dump(eval(name), open(name, 'w'))

    dump('products')
    dump('sales')

def record_sale(sale):
    ''' Add **Sale** ``sale`` to ``sales`` dict '''
    sales[sale.time] = sale
    for i in sale.products: i.remove()

def sale():
    upc = None
    parr = []
    tot = 0
    print 'Process sale\n Enter 0 to end entry'
    while True:
        upc = raw_input('Enter upc: ')
        if upc == '0': break
        try:
            product = products[upc]
            parr.append(product)
            print product
            tot += product.price
        except KeyError:
            print 'Product does not exist.'
            continue

    if parr:
        print 'Total $' + str(tot)
        cname = raw_input('Enter customer name: ')
        pmethod = raw_input('Enter payment method: ')
        comment = raw_input('If desired, enter comment: ')
        record_sale(Sale(parr, cname, pmethod, comment if comment else None)) 

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
    while True:
        if raw_input('q to quit: ') == 'q': break
        sale()

shutdown()
