# HTTP - Customer Endpoints

## Endpoints

**Rest Controller style:**

- [GET `/rc/customers` - Retrieve all customers](#GET-rccustomers---retrieve-all-customers)

**Functional Style:**

- [GET `/fe/customers` - Retrieve all customers](#GET-fecustomers---retrieve-all-customers)

### Rest Controller Endpoints

#### GET `/rc/customers` - Retrieve all customers

**Body Request:** N/A\
**Body Response:** List of [`Customer`](./Customer.kt)\
**Response Status:** `200 OK` (if successful)

**cUrl Example:**

```bash
curl localhost:8080/rc/customers
```

### Functional Endpoints

#### GET `/fe/customers` - Retrieve all customers

**Body Request:** N/A\
**Body Response:** List of [`Customer`](./Customer.kt)\
**Response Status:** `200 OK` (if successful)

**cUrl Example:**
```bash
curl localhost:8080/fe/customers
```
