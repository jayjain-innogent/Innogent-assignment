import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { getProductById } from "../api/productAPI";
import { Container, Row, Col, Card, Spinner, Alert } from "react-bootstrap"; // added bootstrap components

function ProductDetails() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function fetchProduct() {
            try {
                const data = await getProductById(id);

                // Extra edge case handling: if data missing or invalid
                if (!data || !data.id) {
                    throw new Error("Product not found");
                }

                setProduct(data);
            } catch (err) {
                setError(err.message || "Failed to load product");
            } finally {
                setLoading(false);
            }
        }

        fetchProduct();
    }, [id]);

    // Handle loading state
    if (loading) {
        return (
            <Container className="text-center mt-5">
                <Spinner animation="border" role="status" />
                <p className="mt-3">Loading product details...</p>
            </Container>
        );
    }

    // Handle error state
    if (error) {
        return (
            <Container className="mt-5">
                <Alert variant="danger" className="text-center">
                    {error}
                </Alert>
            </Container>
        );
    }

    // Handle product not found
    if (!product) {
        return (
            <Container className="mt-5">
                <Alert variant="warning" className="text-center">
                    Product not found
                </Alert>
            </Container>
        );
    }

    // Final Product UI
    return (
        <Container className="mt-5">
            <Row className="justify-content-center align-items-center">
                {/* Left side - image */}
                <Col md={5} className="text-center">
                    <Card className="shadow-sm border-0">
                        <Card.Img
                            variant="top"
                            src={product.image || "/assets/placeholder.png"}
                            alt={product.title || "Product Image"}
                            style={{ height: "350px", objectFit: "contain", padding: "20px" }}
                            onError={(e) => (e.target.src = "/assets/placeholder.png")} // fallback image
                        />
                    </Card>
                </Col>

                {/* Right side - product details */}
                <Col md={6}>
                    <h2 className="fw-bold mb-3">{product.title || "Unknown Product"}</h2>
                    <p className="text-muted mb-2">
                        Category: <span className="fw-semibold">{product.category || "Not Available"}</span>
                    </p>
                    <p className="mb-4">{product.description || "No description available."}</p>
                    <h4 className="text-success fw-bold mb-3">
                        ₹ {product.price ? product.price.toFixed(2) : "N/A"}
                    </h4>
                    <div className="product-rating text-warning">
                        ⭐ {product.rating?.rate ?? "N/A"} ({product.rating?.count ?? 0} reviews)
                    </div>
                </Col>
            </Row>
        </Container>
    );
}

export default ProductDetails;
