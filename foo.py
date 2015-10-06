import Register

reg = Register()

def cash_change(total, paid=None):
	while paid is None:
		try:
			paid = float(raw_input('Enter cash paid: '))
		except ValueError:
			print 'Invalid value'

	return paid - total

def sale():
	upc = None
	qty = None
	print 'Process sale\n Enter 0 to end entry\n Q to quit'
	while True:
		upc = raw_input('Enter upc: ')
		if upc in ('Q', 'q'): return False # Enables rest of code to know user wants to quit
		if upc == '0': break
		try:
			upc = int(upc)
			reg.createNewSale()
			while qty == None:
				try: # Product multipler
					sqty = raw_input('If not 1, enter quanity: ')
					qty = int(sqty) if sqty != '' else 1
					if qty < 0: raise ValueError
				except ValueError:
					print 'Invalid quanity'
					qty = None
			
			if reg.enterItem(upc, qty) is None: pass#raise KeyError
			reg.makePayment("cash", reg.getCurrentSale().getTotal())
		except (KeyError, ValueError):
			print 'Product does not exist.'
			continue
	if qty > 1:
		reg.endSale()
		print 'Total $' + reg.getTotal()
		cname = raw_input('Enter customer name: ')
		pmethod = raw_input('Enter payment method: ')
		comment = raw_input('If desired, enter comment: ')
		s = Sale(parr, cname, pmethod, comment if comment else None)
		record_sale(s)

	return True


while True:
	while sale(): # Run until sale() returns False
		if reg.getCurrentSale() is None: continue
		print '$' + str(cash_change(reg.getCurrentSale().getTotal())) + ' owed'

reg.cutConnection()
