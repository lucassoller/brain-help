import React from 'react';

export default function Alert({ text, alertType = 'danger', onClose }) {
    if (!text) return null;

    return (
        <div
            className={`alert alert-${alertType} d-flex justify-content-between align-items-center`}
            role="alert"
            style={{
                marginTop: '16px',
                marginBottom: 0,
                borderRadius: '12px',
                padding: '10px 14px',
                fontSize: '0.9rem'
            }}
        >
            <span>{text}</span>
            {onClose && (
                <button
                    type="button"
                    onClick={onClose}
                    aria-label="Fechar"
                    style={{
                        background: 'transparent',
                        border: 'none',
                        fontSize: '1.1rem',
                        fontWeight: 'bold',
                        lineHeight: 1,
                        cursor: 'pointer',
                        color: 'inherit',
                        marginLeft: '12px',
                        padding: 0
                    }}
                >
                    ✕
                </button>
            )}
        </div>
    );
}