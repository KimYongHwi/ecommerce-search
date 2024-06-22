import React from 'react';

function ProductList({ products, total }) {
  return (
    <div className='products-container grid grid-cols-2 md:grid-cols-4 gap-4'>
      {products.map((product) => {
        console.log(product)
        return (
          <div class='max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700'>
            <a href='#'>
              <img class='rounded-t-lg' src={product.imageUrl}/>
            </a>
            <div class='p-5'>
              <a href='#'>
                <h5 class='mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white'>
                  {product.productDisplayName}
                </h5>
              </a>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.articleType}
              </p>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.baseColor}
              </p>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.gender}
              </p>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.mainCategory}
              </p>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.subCategory}
              </p>
              <p class='mb-3 font-normal text-gray-700 dark:text-gray-400'>
                {product.usage}
              </p>
            </div>
          </div>
        );
      })}

      <nav aria-label='Page navigation example'>
        <ul class='inline-flex -space-x-px text-sm'>
          <li>
            <a
              href='#'
              class='flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'
            >
              Previous
            </a>
          </li>
          <li>
            <a
              href='#'
              class='flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'
            >
              1
            </a>
          </li>
          <li>
            <a
              href='#'
              class='flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white'
            >
              Next
            </a>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default ProductList;
