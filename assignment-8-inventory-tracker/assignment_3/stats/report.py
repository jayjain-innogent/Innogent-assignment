import numpy as np
from models.product import Product
from models.food_product import FoodProduct
from models.electronics_product import ElectronicsProduct

# 1. OVERALL INVENTORY STATISTICS
def show_stats(products):
    try:
        if not products:
            print("No products available for statistics.")
            return

        prices = np.array([p.price for p in products])
        stocks = np.array([p.stock for p in products])
        values = prices * stocks

        print("\nINVENTORY STATISTICS REPORT")
        print("======================================")
        print(f"Average Price of Items            : ₹{np.mean(prices):.2f}")
        print(f"Most Expensive Item Price         : ₹{np.max(prices):.2f}")
        print(f"Total Count of All Items in Stock : {np.sum(stocks)}")
        print(f"Total Inventory Value (All Items) : ₹{np.sum(values):.2f}")

        print("\nIndividual Product Values:")
        for p, val in zip(products, values):
            print(f"  {p.name}: ₹{val:.2f}")

    except Exception as e:
        print(f"Error generating overall statistics: {e}")


# 2. CATEGORY-WISE INVENTORY STATISTICS
def show_stats_by_category(products):
    try:
        if not products:
            print("No products available for category-based statistics.")
            return

        categories = {
            "General Products": [p for p in products if type(p) is Product],
            "Food Products": [p for p in products if isinstance(p, FoodProduct)],
            "Electronics Products": [p for p in products if isinstance(p, ElectronicsProduct)]
        }

        print("\nCATEGORY-WISE INVENTORY STATISTICS")
        print("======================================")

        for category, items in categories.items():
            if not items:
                print(f"\n{category}: No items found.")
                continue

            prices = np.array([p.price for p in items])
            stocks = np.array([p.stock for p in items])
            values = prices * stocks

            print(f"\n{category}:")
            print(f"  Total Items           : {len(items)}")
            print(f"  Average Price         : ₹{np.mean(prices):.2f}")
            print(f"  Total Stock Count     : {np.sum(stocks)}")
            print(f"  Total Inventory Value : ₹{np.sum(values):.2f}")

    except Exception as e:
        print(f"Error generating category statistics: {e}")
