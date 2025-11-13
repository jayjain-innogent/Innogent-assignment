# from models.product import Product

class Inventory:
    def __init__(self):
        self.products = []

    # List all products
    def list_products(self):
        if not self.products:
            print("No products in inventory.")
            return

        for p in self.products:
            print(p)

    # Low stock warning
    def low_stack_warning(self):
        flag = False
        for p in self.products:
            if p.is_low_stock():
                print(f"Warning: Low stock for product '{p.name}'. Only {p.stock} left.")
                flag = True

        if not flag:
            print("All products are sufficiently stocked.")

    # Add product
    def add_product(self, product):
        self.products.append(product)
        print(f"Product '{product.name}' added successfully.")

    # Update stock safely
    def update_stock(self, id, new_stock):
        if new_stock < 0:
            print("Stock cannot be negative.")
            return

        for p in self.products:
            if p.id == id:
                p.stock = new_stock
                print(f"Stock for product '{p.name}' updated to {new_stock}.")
                return

        print(f"Product with ID '{id}' not found in inventory.")

    # Delete product
    def delete_product(self, id):
        for p in self.products:
            if p.id == id:
                self.products.remove(p)
                print(f"Product '{p.name}' deleted successfully.")
                return

        print(f"Product with ID '{id}' not found in inventory.")

    # Total value
    def total_inventory_value(self):
        total_value = 0

        for p in self.products:
            if p.price < 0:
                print(f"Warning: Product '{p.name}' has invalid price. Skipping.")
                continue

            if p.stock < 0:
                print(f"Warning: Product '{p.name}' has invalid stock. Skipping.")
                continue

            total_value += p.price * p.stock

        print(f"Total inventory value: ₹{total_value:.2f}")
        return total_value

    # Apply discount (UNCHANGED as requested)
    def apply_discount(self, tags, percent=0.5):
        for p in self.products:
            if ('clearance' in p.tags) and (tags & p.tags):
                original_price = p.price
                p.price *= (1 - percent)
                print(f"Discounted '{p.name}' from ₹{original_price:.2f} to ₹{p.price:.2f}")
