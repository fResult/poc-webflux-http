# HTTP - Customer Endpoints

## Endpoints

**Rest Controller style:**

- [GET `/rc/customers` - Retrieve all customers](#GET-rccustomers---retrieve-all-customers)

**Functional Style:**

- [GET `/fe/customers` - Retrieve all customers](#GET-fecustomers---retrieve-all-customers)

### Rest Controller Endpoints

#### GET `/rc/customers` - Retrieve all customers

**Body Request:** N/A\
**Body Response:** List of [`Customer`][CustomerEntity]\
**Response Status:** `200 OK` (if successful)

**cUrl Example:**

```bash
curl localhost:8080/rc/customers
```

#### GET `/rc/customers/{id}` - Retrieve a customer by ID

**Body Request:** N/A\
**Body Response:** [`Customer`][CustomerEntity]

**Response Status:**
- `200 OK` (if successful)
- `404 Not Found` (if customer not found)

**cUrl Example:**

```bash
curl localhost:8080/rc/customers/1
```

### Functional Endpoints

#### GET `/fe/customers` - Retrieve all customers

**Body Request:** N/A\
**Body Response:** List of [`Customer`][CustomerEntity]\
**Response Status:** `200 OK` (if successful)

**cUrl Example:**

```bash
curl localhost:8080/fe/customers
```

#### GET `/fe/customers/{id}` - Retrieve a customer by ID

**Body Request:** N/A\
**Body Response:** [`Customer`][CustomerEntity]

**Response Status:**

- `200 OK` (if successful)
- `404 Not Found` (if customer not found)

**cUrl Example:**

```bash
curl localhost:8080/fe/customers/1
```


[CustomerEntity]: ./Customer.kt
