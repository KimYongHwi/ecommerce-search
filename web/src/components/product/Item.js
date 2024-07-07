import '../../assets/css/item.css';
import React from 'react';

function Item({ products }) {
  return (
    <div class='col-span-3'>
      {/* sorting */}
      <div class='flex items-center mb-4'>
        <select
          name='sort'
          id='sort'
          class='w-44 text-sm text-gray-600 py-3 px-4 border-gray-300 shadow-sm rounded focus:ring-primary focus:border-primary'
        >
          <option value=''>Default sorting</option>
          <option value='price-low-to-high'>Price low to high</option>
          <option value='price-high-to-low'>Price high to low</option>
          <option value='latest'>Latest product</option>
        </select>

        <div class='flex gap-2 ml-auto'>
          <div class='border border-primary w-10 h-9 flex items-center justify-center text-white bg-primary rounded cursor-pointer'>
            <i class='fa-solid fa-grip-vertical'></i>
          </div>
          <div class='border border-gray-300 w-10 h-9 flex items-center justify-center text-gray-600 rounded cursor-pointer'>
            <i class='fa-solid fa-list'></i>
          </div>
        </div>
      </div>

      {/* item */}
      <div class='grid md:grid-cols-6 grid-cols-6 gap-6'>
        {products.map((p) => (
          <div class='bg-white shadow rounded overflow-hidden group'>
            <div class='relative'>
              <img src={p.imageUrl} alt='product 1' class='w-full' />
              <div class='absolute inset-0 bg-black bg-opacity-40 flex items-center justify-center gap-2 opacity-0 group-hover:opacity-100 transition'>
                <a
                  href='#'
                  class='text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition'
                  title='view product'
                >
                  <i class='fa-solid fa-magnifying-glass'></i>
                </a>
                <a
                  href='#'
                  class='text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition'
                  title='add to wishlist'
                >
                  <i class='fa-solid fa-heart'></i>
                </a>
              </div>
            </div>
            <div class='pt-4 pb-3 px-4'>
              <a href='#'>
                <h4 class='font-medium text-m mb-2 text-gray-800 hover:text-primary transition item-name'>
                  {p.productDisplayName}
                </h4>

              </a>
              <div class='badges'>
                <span class='bg-blue-100 text-blue-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-blue-900 dark:text-blue-300'>
                  {p.gender}
                </span>
                <span class='bg-gray-100 text-gray-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-gray-300'>
                  {p.season}
                </span>
              </div>

              <div class='categories'>
                <nav class='flex' aria-label='Breadcrumb'>
                  <ol class='inline-flex items-center space-x-1 md:space-x-2 rtl:space-x-reverse'>
                    <li class='inline-flex items-center'>
                      <p>{p.mainCategory}</p>
                    </li>

                    <li>
                      <div class='flex items-center'>
                        <svg
                          class='rtl:rotate-180 w-3 h-3 text-gray-400 mx-1'
                          aria-hidden='true'
                          xmlns='http://www.w3.org/2000/svg'
                          fill='none'
                          viewBox='0 0 6 10'
                        >
                          <path
                            stroke='currentColor'
                            stroke-linecap='round'
                            stroke-linejoin='round'
                            stroke-width='2'
                            d='m1 9 4-4-4-4'
                          />
                        </svg>
                        <p>{p.subCategory}</p>
                      </div>
                    </li>
                  </ol>
                </nav>
              </div>

              <div class='flex items-baseline mb-1 space-x-2'>
                <p class='text-xl text-primary font-semibold'>$45.00</p>
                <p class='text-sm text-gray-400 line-through'>$55.90</p>
              </div>
              <div class='flex items-center'>
                <div class='flex gap-1 text-sm text-yellow-400'>
                  <span>
                    <i class='fa-solid fa-star'></i>
                  </span>
                  <span>
                    <i class='fa-solid fa-star'></i>
                  </span>
                  <span>
                    <i class='fa-solid fa-star'></i>
                  </span>
                  <span>
                    <i class='fa-solid fa-star'></i>
                  </span>
                  <span>
                    <i class='fa-solid fa-star'></i>
                  </span>
                </div>
              </div>
            </div>
            <a
              href='#'
              class='block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition'
            >
              Add to cart
            </a>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Item;
