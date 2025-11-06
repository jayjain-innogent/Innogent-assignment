import React, { useContext, useMemo, useState } from "react";
import { ProductContext } from "../context/ProductContext";
import { useNavigate } from "react-router-dom";
import { Container, Row, Col, Card, Spinner } from "react-bootstrap"; // added for bootstrap layout

function Home() {

    //Access data from context
    const { products, loading, error } = useContext(ProductContext);

    // Hook for navigation
    const navigate = useNavigate();

    //Track selected category
    const [selectedCategory, setSelectedCategory] = useState("All");

    //Track Search input value
    const [searchTerm, setSearchTerm] = useState("");

    //Generate unique categories from products
    const categories = useMemo(() => {
        if (!products || products.length === 0) return ["All"];
        const unique = new Set(products.map((p) => p.category));
        return ["All", ...unique]; //All for showing everything
    }, [products]);

    //Filter products based on category and search
    const filteredProducts = useMemo(() => {
        let filtered = products;

        //Category filter
        if (selectedCategory !== "All") {
            filtered = filtered.filter((p) => p.category === selectedCategory);
        }

        //Search filter
        if (searchTerm.trim() !== "") {
            filtered = filtered.filter((p) =>
                p.title.toLowerCase().includes(searchTerm.toLowerCase())
            );
        }

        return filtered;
    }, [selectedCategory, products, searchTerm]);

    // Handle loading state
    if (loading) {
        return (
            <Container className="text-center mt-5">
                <Spinner animation="border" role="status" />
                <p className="mt-3">Loading product details...</p>
            </Container>
        );
    }

    //Handle error state
    if (error) {
        return <h3 style={{ color: "red" }}>Error: {error}</h3>;
    }

    //Handle empty data state
    if (products.length === 0) {
        return <h3>No products available</h3>;
    }

    //IF all good, render product
    return (
        <div className="products-list">
            <h2>All Products</h2>

            {/* Search bar */}
            <div className="search-bar">
                <input
                    type="text"
                    placeholder="Search products..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="search-input"
                />
            </div>

            {/* Display Category Buttons */}
            <div className="category-filter">
                {categories.map((cat) => (
                    <button
                        key={cat}
                        className={`category-btn ${selectedCategory === cat ? "active" : ""}`}
                        onClick={() => setSelectedCategory(cat)}
                    >
                        {cat}
                    </button>
                ))}
            </div>

            <Container className="mt-4">
                <Row className="g-4">
                    {filteredProducts.map((product) => (
                        <Col
                            key={product.id}
                            xs={12}
                            sm={6}
                            md={4}
                            lg={3}
                            onClick={() => navigate(`/product/${product.id}`)}
                            style={{ cursor: "pointer" }}
                        >
                            <Card className="h-100 shadow-sm border-0">
                                <Card.Img
                                    variant="top"
                                    src={product.image}
                                    alt={product.title}
                                    style={{ height: "200px", objectFit: "contain", padding: "15px" }}
                                />
                                <Card.Body className="text-center">
                                    <Card.Title style={{ fontSize: "15px" }}>
                                        {product.title}
                                    </Card.Title>
                                    <Card.Text className="fw-bold">
                                        ₹ {product.price}
                                    </Card.Text>
                                    <Card.Text className="text-muted" style={{ fontSize: "14px" }}>
                                        ⭐ {product.rating?.rate || "N/A"} ({product.rating?.count || 0})
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container>

        </div>
    );
}

export default Home;
