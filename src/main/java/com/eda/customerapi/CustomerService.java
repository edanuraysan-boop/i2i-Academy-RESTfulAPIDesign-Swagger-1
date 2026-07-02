package com.eda.customerapi;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setCity(request.getCity());

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponseDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail(),
                savedCustomer.getPhone(),
                savedCustomer.getCity()
        );
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null) {
            return null;
        }

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCity()
        );
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerResponseDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone(),
                        customer.getCity()))
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO request) {

        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null) {
            return null;
        }

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setCity(request.getCity());

        Customer updated = customerRepository.save(customer);

        return new CustomerResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getPhone(),
                updated.getCity()
        );
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}