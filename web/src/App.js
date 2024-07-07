import './App.css';
import Header from './components/Header';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Main from './pages/Main';
import Search from './pages/Search';

function App() {
  return (
    <body>
      <BrowserRouter>
        <Header setProducts={setProducts} setTotal={setTotal} />
        <Navbar />

        <Routes>
          <Route path='/' element={<Main />}></Route>
          <Route path='/search/:keyword' element={<Search />}></Route>
        </Routes>

        <Footer />
      </BrowserRouter>
    </body>
  );
}

export default App;
