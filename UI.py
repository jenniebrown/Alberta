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
			
			if not reg.enterItem(upc, qty): raise KeyError
			reg.makePayment("cash", reg.getCurrentSale().getTotal())
		except (KeyError, ValueError):
			print 'Product does not exist.'
			qty = None
			continue
	if qty > 0:
		reg.endSale()
		print 'Total $' + str(reg.getCurrentSale().getTotal())

	return True


while sale(): # Run until sale() returns False
	if reg.getCurrentSale() is None: continue
	print '$' + str(cash_change(reg.getCurrentSale().getTotal())) + ' owed'

reg.cutConnection()
