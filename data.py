from datetime import datetime

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
    def __init__(self, product, cname, pmethod, comment=None):
        '''
        * product (Product) The product purchased
        * cname (string)    Customer name
        * pmethod (string)  Payment method
        * comment (string)
        '''
        self.time = datetime.now() # Time of sale
        self.product = product
        self.cname = cname
        self.pmethod = pmethod
        self.comment = comment

    # @staticmethod
    # def load(name='sales.txt'):
    #     ret = {}

    #     for line in open(name, 'r'):
    #         arr = [i.strip() for i in line[:len(line)-1].split('\t')]
    #         ret[arr[0]] = Sale(arr[0], arr[1], arr[2], arr[3]) if len(ret) == 4 else ret[arr[0]] = Sale(arr[0], arr[1], arr[2])

    #     return ret
