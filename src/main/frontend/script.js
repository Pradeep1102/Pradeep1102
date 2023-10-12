const apiUrl = 'http://localhost:8080/api/todos';  // Replace with your API URL

async function fetchTodos() {
  try {
    const response = await fetch(apiUrl);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching todos:', error);
  }
}

async function addTodo(task) {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ task, completed: false })
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error adding todo:', error);
  }
}

async function updateTodoStatus(id, completed) {
  try {
    const response = await fetch(`${apiUrl}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ completed })
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error updating todo status:', error);
  }
}

// Usage examples

// Fetch all todos
fetchTodos().then(todos => {
  console.log('Todos:', todos);
});

// Add a todo
addTodo('Buy groceries').then(todo => {
  console.log('Added todo:', todo);
});

// Update todo status
updateTodoStatus(1, true).then(todo => {
  console.log('Updated todo:', todo);
});
