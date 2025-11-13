LOW_STOCK = 5
import itertools

#Product Class
class Product:
    _id_counter = itertools.count(1)

    def __init__(self, name, stock, price, location, tags=None):
        self.id = next(Product._id_counter)
        self.name = name
        self.stock = stock
        self.price = price
        self.location = location
        self.tags = set(tags) if tags else set()

    def is_low_stock(self):
        return self.stock <= LOW_STOCK

    def __str__(self):
        tags_str = ', '.join(self.tags) if self.tags else 'None'
        return (
            f"\n   Id       : {self.id}"
            f"\n   Name     : {self.name}"
            f"\n   Stock    : {self.stock}"
            f"\n   Price    : ₹{self.price:.2f}"
            f"\n   Location : {self.location}"
            f"\n   Tags     : {tags_str}\n"
            "---------------------------"
        )


# Inventory Class
class Inventory:
    def __init__(self):
        self.products = []

    def list_products(self):
        if not self.products:
            print("No products found.")
            return
        for p in self.products:
            print(p)

    def low_stock_warning(self):
        found = False
        for p in self.products:
            if p.is_low_stock():
                print(f"⚠ Low Stock: '{p.name}' → Only {p.stock} left.")
                found = True
        if not found:
            print("All products have sufficient stock.")

    def add_product(self, product):
        self.products.append(product)
        print(f"Product '{product.name}' added successfully.")

    def update_stock(self, product_id, new_stock):
        for p in self.products:
            if p.id == product_id:
                p.stock = new_stock
                print(f"Stock updated → '{p.name}' now has {new_stock}.")
                return
        print(f"Product with ID '{product_id}' not found.")

    def delete_product(self, product_id):
        for p in self.products:
            if p.id == product_id:
                self.products.remove(p)
                print(f"Product '{p.name}' deleted successfully.")
                return
        print(f"Product with ID '{product_id}' not found.")

    def total_inventory_value(self):
        total = sum(p.price * p.stock for p in self.products)
        print(f"Total Inventory Value: ₹{total:.2f}")
        return total

    def apply_discount(self, tags, percent = 0.5):
        discounted = False
        for p in self.products:
            # Original requirement: discount only if product is clearance + matching tags
            if "clearance" in p.tags and (tags & p.tags):
                old_price = p.price
                p.price *= (1 - percent)
                print(
                    f"Discounted '{p.name}' → ₹{old_price:.2f} → ₹{p.price:.2f}"
                )
                discounted = True

        if not discounted:
            print("No clearance products matched the given tags.")



# Safe Input Helpers

def safe_int(prompt):
    while True:
        try:
            value = int(input(prompt))
            if value <= 0:
                print("Enter a positive integer.")
                continue  
            return value
        except ValueError:
            print("Enter a valid integer.")


def safe_float(prompt):
    while True:
        try:
            value = float(input(prompt))
            if value <= 0:
                print("Enter a positive number.")
                continue   
            return value
        except ValueError:
            print("Enter a valid number.")


def safe_non_empty(prompt):
    while True:
        value = input(prompt).strip()
        if value:
            return value
        print("Input cannot be empty.")



# Product Creation Input 
def product_input():
    name = safe_non_empty("Enter product name: ")
    stock = safe_int("Enter stock: ")
    price = safe_float("Enter price: ")
    location = safe_non_empty("Enter location: ")
    tags = {
        tag.strip() for tag in input("Enter tags (comma-separated): ").split(',')
        if tag.strip()
    }
    return Product(name, stock, price, location, tags)


# Main Program
def main():
    inventory = Inventory()

    # Preloaded products
    inventory.products = [
    Product("Amul Milk", 20, 50.0, "Shelf-1", {"dairy", "fresh"}),
    Product("Mango", 15, 30.0, "Shelf-2", {"fruit", "seasonal", "clearance"}),
    Product("Maggi", 50, 12.0, "Shelf-3", {"grocery", "instant"}),
    Product("Britannia Biscuits", 40, 25.0, "Shelf-4", {"snack", "grocery", "clearance"}),
    Product("Tata Tea", 30, 150.0, "Shelf-5", {"beverage", "grocery"}),
    Product("Spinach", 10, 20.0, "Shelf-1", {"vegetable", "fresh"}),
    Product("Parle-G", 60, 5.0, "Shelf-4", {"snack", "grocery"}),
    Product("Dettol Soap", 25, 40.0, "Shelf-6", {"hygiene", "essential", "clearance"}),
    Product("Coca-Cola", 45, 60.0, "Shelf-5", {"beverage", "refreshing"}),
    ]


    while True:
        print("""
        ------------ Inventory Management ------------
        1. List Products
        2. Low Stock Warnings
        3. Add Product
        4. Update Stock
        5. Delete Product
        6. Total Inventory Value
        7. Apply Discount by Tags
        8. Exit
        ----------------------------------------------
        """)

        choice = input("Enter your choice (1-8): ").strip()

        if choice == '1':
            inventory.list_products()

        elif choice == '2':
            inventory.low_stock_warning()

        elif choice == '3':
            product = product_input()
            inventory.add_product(product)

        elif choice == '4':
            pid = safe_int("Enter product ID: ")
            stock = safe_int("Enter new stock: ")
            inventory.update_stock(pid, stock)

        elif choice == '5':
            pid = safe_int("Enter product ID: ")
            inventory.delete_product(pid)

        elif choice == '6':
            inventory.total_inventory_value()

        elif choice == '7':
            tag_input = input("Enter tags (comma-separated): ").strip()
            tags = {t.strip() for t in tag_input.split(",") if t.strip()}
            inventory.apply_discount(tags)

        elif choice == '8':
            print("Exiting system. Goodbye!")
            break

        else:
            print("Invalid choice. Choose between 1–8.")


if __name__ == "__main__":
    main()
