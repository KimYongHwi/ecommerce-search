import React, { useState } from 'react';
import TextInput from './TextInput';
import { useNavigate } from 'react-router-dom';

function Header() {
  const navigate = useNavigate();
  const [formValue, setFormValue] = useState({ keyword: '' });

  function handleSubmit(e) {
    e.preventDefault();

    if (formValue.keyword != '') {
      navigate(`/search/${formValue.keyword}`);
    }
  }

  return (
    <div class='px-4 py-4 bg-white'>
      <div class='flex justify-center'>
        <form class='w-1/2' onSubmit={handleSubmit}>
          <label for='search' class='mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white'>
            Search
          </label>
          <div class='relative'>
            <div class='absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none'>
              <svg
                class='w-4 h-4 text-gray-500 dark:text-gray-400'
                aria-hidden='true'
                xmlns='http://www.w3.org/2000/svg'
                fill='none'
                viewBox='0 0 20 20'
              >
                <path
                  stroke='currentColor'
                  stroke-linecap='round'
                  stroke-linejoin='round'
                  stroke-width='2'
                  d='m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z'
                />
              </svg>
            </div>

            <TextInput
              value={formValue.keyword}
              setValue={(value) => {
                setFormValue((state) => ({ ...state, keyword: value }));
              }}
            />

            <button
              type='submit'
              class='text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800'
            >
              Search
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Header;
