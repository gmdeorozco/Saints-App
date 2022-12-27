import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import 'bootstrap/dist/css/bootstrap.css';
import { BsPlusLg } from 'react-icons/bs';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';


function App() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        <BsPlusLg />
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title> New Saint </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          
          <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label> Saint's name: </Form.Label>
              <Form.Control type="text" placeholder="Enter Saint's name" />
              <Form.Text className="text-muted">
                Enter Saint's name.
              </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
              <Form.Label> Quote: </Form.Label>
              <Form.Control as="textarea" rows={2} />
            </Form.Group>

            <Form.Label htmlFor="inputPassword5"> Canonization Date: </Form.Label>
              <Form.Control
                type="date"
                id="inputPassword5"
                aria-describedby="passwordHelpBlock"
              />

            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>




        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default App;
