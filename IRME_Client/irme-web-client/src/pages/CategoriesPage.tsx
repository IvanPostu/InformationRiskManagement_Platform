import React, { ReactElement } from 'react'
import { Loader } from '../components/Loader'
import { MainLayout } from '../layouts/MainLayout'

export function CategoriesPage(): ReactElement {
    return (
        <MainLayout>
            <div style={{ marginTop: '30px' }} className="container">
                <Loader />
            </div>
            <div className="container">
                <div className="row">
                    <div className="col s12 m4">
                        <div className="card blue-grey darken-1">
                            <div className="card-content white-text">
                                <span className="card-title">Card Title</span>
                                <p>
                                    I am a very simple card. I am good at containing small bits of information. I am
                                    convenient because I require little markup to use effectively.
                                </p>
                            </div>
                            <div className="card-action">
                                <a href="#">This is a link</a>
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                    <div className="col s12 m4">
                        <div className="card blue-grey darken-1">
                            <div className="card-content white-text">
                                <span className="card-title">Card Title</span>
                                <p>
                                    I am a very simple card. I am good at containing small bits of information. I am
                                    convenient because I require little markup to use effectively.
                                </p>
                            </div>
                            <div className="card-action">
                                <a href="#">This is a link</a>
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                    <div className="col s12 m4">
                        <div className="card blue-grey darken-1">
                            <div className="card-content white-text">
                                <span className="card-title">Card Title</span>
                                <p>
                                    I am a very simple card. I am good at containing small bits of information. I am
                                    convenient because I require little markup to use effectively.
                                </p>
                            </div>
                            <div className="card-action">
                                <a href="#">This is a link</a>
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                    <div className="col s12 m4">
                        <div className="card blue-grey darken-1">
                            <div className="card-content white-text">
                                <span className="card-title">Card Title</span>
                                <p>
                                    I am a very simple card. I am good at containing small bits of information. I am
                                    convenient because I require little markup to use effectively.
                                </p>
                            </div>
                            <div className="card-action">
                                <a href="#">This is a link</a>
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                    <div className="col s12 m4">
                        <div className="card blue-grey darken-1">
                            <div className="card-content white-text">
                                <span className="card-title">Card Title</span>
                                <p>
                                    I am a very simple card. I am good at containing small bits of information. I am
                                    convenient because I require little markup to use effectively.
                                </p>
                            </div>
                            <div className="card-action">
                                <a href="#">This is a link</a>
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </MainLayout>
    )
}
