import { Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import ProductDetails from "./pages/ProductDetails";
import Cart from "./pages/Cart";
import Notifications from "./pages/Notifications";
import Profile from "./pages/Profile";
import './App.css';
import { ProductProvider } from "./context/ProductContext";


export default function App() {

  return (
    // Router wraps the entire app to enable routing

    <>
      <ProductProvider>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/product/:id" element={<ProductDetails />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/notifications" element={<Notifications />} />
          <Route path="/profile" element={<Profile />} />
        </Routes>
        <Footer />
      </ProductProvider>
    </>
  );


}
