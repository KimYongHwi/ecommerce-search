import axios from 'axios';
import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'react-router-dom';
import Item from '../components/product/Item';
import Breadcrumb from '../components/Breadcrumb';

function Search() {
  const { keyword } = useParams();
  const [products, setProducts] = useState([]);
  const [total, setTotal] = useState(0);
  const wasAlreadyRequested = useRef(false);

  const searchProducts = async (keyword, page, size) => {
    axios
      .get(`http://localhost:8080/products/search?keyword=${keyword}&page=${page}&size=${size}`)
      .then((res) => {
        setProducts(res.data.products);
        setTotal(res.data.total);
      })
      .catch((e) => {
        console.error(e.message);
      });
  };

  useEffect(() => {
    if (!wasAlreadyRequested.current) {
      searchProducts(keyword, 0, 24);
      wasAlreadyRequested.current = true;
    }
  }, [keyword]);

  return (
    <div class='container pb-16'>
      <Breadcrumb />
      <Item products={products} />
    </div>
  );
}

export default Search;
