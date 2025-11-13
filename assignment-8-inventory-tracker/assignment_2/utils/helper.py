# Product Input Methods
from models.product import Product
from models.food_product import FoodProduct
from models.electronics_product import ElectronicsProduct

# Safe Input Helper Methods
def safe_int(prompt):
    while True:
        value = input(prompt).strip()

        if value == "":
            print("Value cannot be empty. Please enter a number.")
            continue

        # Integer validation
        if value.isdigit() or (value.startswith('-') and value[1:].isdigit()):
            return int(value)

        print("Invalid number. Please enter a valid integer.")


def safe_float(prompt):
    while True:
        value = input(prompt).strip()

        if value == "":
            print("Value cannot be empty. Please enter a number.")
            continue

        try:
            return float(value)
        except ValueError:
            print("Invalid decimal number. Please enter a valid float.")


def safe_input_empty(prompt):
    value = input(prompt)
    return value.strip() if value else ""

# Tag Input
def input_tags():
    tags_input = safe_input_empty("Enter product tags (comma-separated): ")
    return {tag.strip() for tag in tags_input.split(',') if tag.strip()}



def input_product():
    name = safe_input_empty("Enter product name: ")
    stock = safe_int("Enter stock: ")
    price = safe_float("Enter price: ")
    location = safe_input_empty("Enter location: ")
    tags = input_tags()

    return Product(name, stock, price, location, tags)


def input_food_product():
    name = safe_input_empty("Enter product name: ")
    stock = safe_int("Enter stock: ")
    price = safe_float("Enter price: ")
    location = safe_input_empty("Enter location: ")
    tags = input_tags()
    expiration_date = safe_input_empty("Enter expiration date (YYYY-MM-DD): ")

    return FoodProduct(name, stock, price, location, tags, expiration_date)


def input_electronics_product():
    name = safe_input_empty("Enter product name: ")
    stock = safe_int("Enter stock: ")
    price = safe_float("Enter price: ")
    location = safe_input_empty("Enter location: ")
    tags = input_tags()
    warranty_period = safe_input_empty("Enter warranty period (in years): ")

    return ElectronicsProduct(name, stock, price, location, tags, warranty_period)
