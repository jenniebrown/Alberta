import settings
from time import time
from datetime import datetime
from math import ceil

def roundup(n): return ceil(n * 100) / 100.0

class Product(object):
    """Represents product"""
    def __init__(self, upc, name, price, stock, description):
        '''
        * upc (string)
        * name (string)
        * price (float)
        * stock (int)
        * description (string)
        '''
        self.upc = upc
        self.name = name
        self.price = price
        self.stock = stock
        self.description = description

    def __str__(self):
        return self.upc + ': ' + self.name + ' (' + str(self.stock) + ')\n\t$' + str(self.price) + '\n\t' + self.description

    def remove(self, num=1):
        self.stock -= num

    @staticmethod
    def load(name='products.txt'):
        ret = {}

        for line in open(name, 'r'):
            arr = [i.strip() for i in line[:len(line)-1].split('\t')]
            ret[arr[0]] = Product(arr[0], arr[1], float(arr[2]), int(arr[3]), arr[4])

        return ret

class Sale(object):
    ''' Represents Sale '''
    def __init__(self, products, cname, pmethod, comment=None):
        '''
        * products (list of Product) The products purchased
        * cname (string) Customer name
        * pmethod (string) Payment method
        * comment (string)
        '''
        self.time = time() # Time of sale as seconds since epoch
        self.products = products
        self.cname = cname
        self.pmethod = pmethod
        self.comment = comment
        self.cost = roundup(sum(i.price for i in self.products) * (1 + settings.tax))

    @property
    def datetime(self):
        return datetime.fromtimestamp(self.time)
    
    def __str__(self):
        tmp = ''
        for i in self.products: tmp += (i.name + ', ')
        tmp = tmp[:len(tmp)-2]

        return str(self.datetime) + '  ' + self.cname + '\n\t' + self.pmethod + ' $' + str(self.cost) + '\n\t' + tmp + ('\n\t' +
                                                                                                                self.comment if self.comment else '')
