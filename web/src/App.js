import { useState } from 'react';
import './App.css';
import SearchForm from './SearchFrom';
import ProductList from './ProductList';

function App() {
  const [products, setProducts] = useState([]);
  const [total, setTotal] = useState(0);

  return (
    <div className='App'>
      <header className='App-header'>
        <div class='container mx-auto'>
          {/* <div class='flex flex-row'> */}
          <div class='flex flex-col'>
            <SearchForm setProducts={setProducts} setTotal={setTotal} />
            <ProductList products={products} total={total} />
          </div>
          {/* </div> */}
        </div>
      </header>
    </div>
  );
}

export default App;
